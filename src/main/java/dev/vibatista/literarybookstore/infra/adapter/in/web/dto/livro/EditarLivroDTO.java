package dev.vibatista.literarybookstore.infra.adapter.in.web.dto.livro;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record EditarLivroDTO(

        UUID id,
        @NotBlank
        String titulo,
        String autor,
        LocalDate dataPublicacao,
        BigDecimal preco,
        String genero,
        String descricao

) {
}
