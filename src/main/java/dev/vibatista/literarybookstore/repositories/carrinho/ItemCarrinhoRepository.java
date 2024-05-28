package dev.vibatista.literarybookstore.repositories.carrinho;

import dev.vibatista.literarybookstore.domains.carrinho.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, UUID> {
}
