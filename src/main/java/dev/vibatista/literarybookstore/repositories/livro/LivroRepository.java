package dev.vibatista.literarybookstore.repositories.livro;

import dev.vibatista.literarybookstore.domains.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
