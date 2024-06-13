package dev.vibatista.literarybookstore.domain.repositories.avaliacao;

import dev.vibatista.literarybookstore.domain.models.avaliacao.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID> {
}
