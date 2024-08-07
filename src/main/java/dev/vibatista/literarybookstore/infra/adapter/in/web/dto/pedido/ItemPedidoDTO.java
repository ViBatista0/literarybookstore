package dev.vibatista.literarybookstore.infra.adapter.in.web.dto.pedido;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ItemPedidoDTO(
        @NotNull
        UUID livroId,
        @NotNull
        @Min(1)
        Integer quantidade
) {
}
