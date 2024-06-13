package dev.vibatista.literarybookstore.domain.repositories.carrinho;

import dev.vibatista.literarybookstore.domain.models.carrinho.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarrinhoRepository extends JpaRepository<Carrinho, UUID> {
}
