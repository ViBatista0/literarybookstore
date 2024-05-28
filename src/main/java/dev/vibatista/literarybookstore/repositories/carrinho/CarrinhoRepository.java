package dev.vibatista.literarybookstore.repositories.carrinho;

import dev.vibatista.literarybookstore.domains.carrinho.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarrinhoRepository extends JpaRepository<Carrinho, UUID> {
}
