package dev.vibatista.literarybookstore.infra.adapter.in.web.dto.pedido;

import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import dev.vibatista.literarybookstore.domain.models.pedido.ItemPedido;
import dev.vibatista.literarybookstore.domain.models.pedido.MetodoPagamento;

import java.util.List;
import java.util.UUID;

public record CriarPedidoDTO(
        Cliente cliente,
        List<ItemPedido> itemPedidoList,

        String enderecoEntrega,
        MetodoPagamento metodoPagamento

) {
}
