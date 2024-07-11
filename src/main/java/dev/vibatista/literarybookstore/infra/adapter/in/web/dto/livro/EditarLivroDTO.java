package dev.vibatista.literarybookstore.infra.adapter.in.web.dto.livro;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
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
        public Optional<UUID> getId() {
                return Optional.ofNullable(id);
        }

        public Optional<String> getTitulo() {
                return Optional.ofNullable(titulo);
        }

        public Optional<String> getAutor() {
                return Optional.ofNullable(autor);
        }

        public Optional<LocalDate> getDataPublicacao() {
                return Optional.ofNullable(dataPublicacao);
        }

        public Optional<BigDecimal> getPreco() {
                return Optional.ofNullable(preco);
        }

        public Optional<String> getGenero() {
                return Optional.ofNullable(genero);
        }
}
