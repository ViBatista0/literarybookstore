package dev.vibatista.literarybookstore.domain.repositories.carrinho;

import dev.vibatista.literarybookstore.domain.models.carrinho.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, UUID> {
}
