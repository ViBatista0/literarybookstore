package dev.vibatista.literarybookstore.domain.repositories.livro;

import dev.vibatista.literarybookstore.domain.models.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    Optional<Livro> findByTitulo(String titulo);
}
