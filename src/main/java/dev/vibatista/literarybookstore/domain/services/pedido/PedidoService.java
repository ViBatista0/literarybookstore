package dev.vibatista.literarybookstore.domain.services.pedido;

import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import dev.vibatista.literarybookstore.domain.models.livro.Livro;
import dev.vibatista.literarybookstore.domain.models.pedido.ItemPedido;
import dev.vibatista.literarybookstore.domain.models.pedido.Pedido;
import dev.vibatista.literarybookstore.domain.models.pedido.StatusPedido;
import dev.vibatista.literarybookstore.domain.repositories.cliente.ClienteRepository;
import dev.vibatista.literarybookstore.domain.repositories.livro.LivroRepository;
import dev.vibatista.literarybookstore.domain.repositories.pedido.PedidoRepository;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.pedido.CriarPedidoDTO;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.pedido.ItemPedidoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private LivroRepository livroRepository;

    /*
     *  O cliente deve existir para criar um pedido
     *  Um livro deve existir para ser inserido nos itens
     *  Realizar a inserção dos livros no ItemPedido
     * */
    public Pedido criarPedido(CriarPedidoDTO pedidoDTO) {
        Optional<Cliente> cliente = clienteRepository.findById(pedidoDTO.cliente().getClienteId());

        if (cliente.isEmpty())
            throw new NullPointerException("Não existe nenhum cliente com o id informado!");

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente.get());
        pedido.setEnderecoEntrega(pedidoDTO.enderecoEntrega());
        pedido.setMetodoPagamento(pedidoDTO.metodoPagamento());
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatusPedido(StatusPedido.PENDENTE);

        List<ItemPedido> itemPedidos = criarItensPedido(pedidoDTO, pedido);
        BigDecimal valorTotal = calcularValorTotal(itemPedidos);

        pedido.setItensPedido(itemPedidos);
        pedido.setValorTotal(valorTotal);

        return pedidoRepository.save(pedido);
    }


    private List<ItemPedido> criarItensPedido(CriarPedidoDTO criarPedidoDTO, Pedido pedido) {
        List<ItemPedidoDTO> itens = criarPedidoDTO.itensPedido();

        return criarPedidoDTO.itensPedido().stream()
                .map(itemPedidoDTO -> {
                    if (itemPedidoDTO.quantidade() < 1) {
                        throw new IllegalArgumentException("O número mínimo de livros para realizar um pedido é 1!");
                    }

                    subtrairLivros(itemPedidoDTO.livroId(), itemPedidoDTO.quantidade());

                    Livro livro = livroRepository.findById(itemPedidoDTO.livroId())
                            .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado!"));

                    return new ItemPedido(pedido, livro, itemPedidoDTO.quantidade());
                }).collect(Collectors.toList());
    }

    private BigDecimal calcularValorTotal(List<ItemPedido> itensPedido) {
        return itensPedido.stream().
                map(ItemPedido::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Apenas para teste
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    // O pedido tem que ser listado pelo cliente, não faz sentido eu listar todos os pedidos existentes,
    // se centenas de clientes fizessem vários pedidos, seria burrice listar todos.
    public List<Pedido> listarPedidosByCliente(UUID clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public Pedido cancelarPedido(String pedidoId) {
        Optional<Pedido> pedido = pedidoRepository.findById(UUID.fromString(pedidoId));

        if (pedido.isEmpty())
            throw new NullPointerException("Não foi encontrado nenhum pedido com esse id!");

        if (pedido.get().getStatusPedido() != StatusPedido.PENDENTE)
            throw new IllegalArgumentException("O pedido só pode ser cancelado caso o status esteja PENDENTE!");

        pedido.get().setStatusPedido(StatusPedido.CANCELADO);

        List<?> livrosId =
        pedido.get().getItensPedido().stream().map(itemPedido -> {
            return itemPedido.getLivro().getLivroId();
        }).toList();

        return pedidoRepository.save(pedido.get());
    }

    private void subtrairLivros(UUID livroId, Integer livrosComprados) {
        Optional<Livro> livro = livroRepository.findById(livroId);

        if (livro.isEmpty())
            throw new NullPointerException("Não foi encontrado nenhum livro com o ID informado!");

        Livro livroExists = livro.get();

        if (livroExists.getQtdEstoque() < livrosComprados)
            throw new IllegalArgumentException("Não é possível comprar uma quantidade maior do que o estoque disponível: "
                    + livroExists.getQtdEstoque());

        livroExists.setQtdEstoque(livroExists.getQtdEstoque() - livrosComprados);

        livroRepository.save(livroExists);
    }

    private void devolverLivros(List<?> livrosId) {

        List<Livro> livros = new ArrayList<>();

    }

}
