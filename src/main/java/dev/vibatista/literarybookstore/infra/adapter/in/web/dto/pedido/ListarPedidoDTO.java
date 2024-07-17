package dev.vibatista.literarybookstore.infra.adapter.in.web.dto.pedido;

import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import dev.vibatista.literarybookstore.domain.models.pedido.ItemPedido;
import dev.vibatista.literarybookstore.domain.models.pedido.MetodoPagamento;
import dev.vibatista.literarybookstore.domain.models.pedido.Pedido;
import dev.vibatista.literarybookstore.domain.models.pedido.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ListarPedidoDTO(
        UUID pedidoId,
        Cliente cliente,
        LocalDateTime dataPedido,
        StatusPedido statusPedido,
        List<ItemPedido> itemPedidoList,
        BigDecimal valorTotal,
        String enderecoEntrega,
        MetodoPagamento metodoPagamento

) {
    public static ListarPedidoDTO fromPedido(Pedido pedido) {
        return new ListarPedidoDTO(
                pedido.getPedidoId(),
                pedido.getCliente(),
                pedido.getDataPedido(),
                pedido.getStatusPedido(),
                pedido.getItensPedido(),
                pedido.getValorTotal(),
                pedido.getEnderecoEntrega(),
                pedido.getMetodoPagamento()
        );
    }
}
