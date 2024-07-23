package dev.vibatista.literarybookstore.infra.adapter.in.web.dto.pedido;

import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import dev.vibatista.literarybookstore.domain.models.pedido.ItemPedido;
import dev.vibatista.literarybookstore.domain.models.pedido.MetodoPagamento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record CriarPedidoDTO(
        @NotNull
        Cliente cliente,
        @NotNull
        @Size(min = 1)
        List<ItemPedidoDTO> itensPedido,
        @NotNull
        String enderecoEntrega,
        @NotNull
        MetodoPagamento metodoPagamento

) {
}
