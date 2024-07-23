package dev.vibatista.literarybookstore.infra.adapter.in.web.dto.livro;

import dev.vibatista.literarybookstore.domain.models.livro.FormatoLivro;
import dev.vibatista.literarybookstore.domain.models.livro.Livro;

import java.math.BigDecimal;
import java.util.UUID;

public record ListarLivrosDTO(
        UUID id,
        String titulo,
        String autor,
        String descricao,
        String editora,
        String isbn,
        BigDecimal preco,
        String genero,
        FormatoLivro formato,
        Integer qntdEstoque,
        BigDecimal avaliacao


) {
    public static ListarLivrosDTO fromLivros(Livro livro) {
        return new ListarLivrosDTO(
                livro.getLivroId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getDescricao(),
                livro.getEditora(),
                livro.getIsbn(),
                livro.getPreco(),
                livro.getGenero(),
                livro.getFormato(),
                livro.getQtdEstoque(),
                livro.getAvaliacao()
        );
    }
}
