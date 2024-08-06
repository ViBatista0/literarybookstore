package dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EditarClienteDTO(
        String nome,
        String email,
        String endereco,
        String telefone
) {
}
