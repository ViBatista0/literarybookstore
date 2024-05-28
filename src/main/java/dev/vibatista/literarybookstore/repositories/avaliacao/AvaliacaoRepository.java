package dev.vibatista.literarybookstore.repositories.avaliacao;

import dev.vibatista.literarybookstore.domains.avaliacao.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID> {
}
