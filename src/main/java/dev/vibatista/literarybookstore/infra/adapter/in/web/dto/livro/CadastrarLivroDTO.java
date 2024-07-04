package dev.vibatista.literarybookstore.infra.adapter.in.web.dto.livro;

import dev.vibatista.literarybookstore.domain.models.livro.FormatoLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CadastrarLivroDTO(

        UUID id,

        String titulo,

        String autor,

        String editora,
        String descricao,
        LocalDate dataPublicacao,

        String isbn,

        BigDecimal preco,
        String genero,
        FormatoLivro formatoLivro,

        Integer qntdEstoque,
        String imagemCapa) {

}

