package dev.vibatista.literarybookstore.domain.repositories.pedido;

import dev.vibatista.literarybookstore.domain.models.pedido.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, UUID> {
}
