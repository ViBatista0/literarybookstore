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
        return criarPedidoDTO.itensPedido().stream()
                .map(itemPedidoDTO -> {
                    Livro livro = livroRepository.findById(itemPedidoDTO.livroId())
                            .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado!"));
                    return new ItemPedido(pedido, livro, itemPedidoDTO.quantidade());
                }).collect(Collectors.toList());
    }

    private BigDecimal calcularValorTotal(List<ItemPedido> itensPedido) {
        return itensPedido.stream().
                map(ItemPedido::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    // O pedido tem que ser listado pelo cliente, não faz sentido eu listar todos os pedidos existentes,
    // se centenas de clientes fizessem vários pedidos, seria burrice listar todos.
//    public List<Pedido> listarPedidos(Cliente cliente){
//        UUID clienteId = cliente.getClienteId();
//        clienteRepository.findById(clienteId);
//
//    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

}
