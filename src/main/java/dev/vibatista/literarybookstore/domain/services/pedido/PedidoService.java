package dev.vibatista.literarybookstore.domain.services.pedido;

import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import dev.vibatista.literarybookstore.domain.models.pedido.Pedido;
import dev.vibatista.literarybookstore.domain.repositories.cliente.ClienteRepository;
import dev.vibatista.literarybookstore.domain.repositories.pedido.PedidoRepository;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.pedido.CriarPedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    /*
     *  O cliente deve existir para criar um pedido
     *  Um livro deve existir para ser inserido nos itens
     *  Realizar a inserção dos livros no ItemPedido
     * */
    public Pedido criarPedido(CriarPedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido(pedidoDTO);

        Optional<Cliente> cliente = clienteRepository.findById(pedidoDTO.cliente().getClienteId());

        if (cliente.isEmpty())
            throw new NullPointerException("Não existe nenhum cliente com o id informado!");

        pedido.setCliente(cliente.get());

        return pedidoRepository.save(pedido);
    }

    // O pedido tem que ser listado pelo cliente, não faz sentido eu listar todos os pedidos existentes,
    // se centenas de clientes fizessem vários pedidos, seria burrice listar todos.
//    public List<Pedido> listarPedidos(Cliente cliente){
//        UUID clienteId = cliente.getClienteId();
//        clienteRepository.findById(clienteId);
//
//    }

}
