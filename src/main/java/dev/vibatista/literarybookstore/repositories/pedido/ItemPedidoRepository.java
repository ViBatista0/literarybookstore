package dev.vibatista.literarybookstore.repositories.pedido;

import dev.vibatista.literarybookstore.domains.pedido.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, UUID> {
}
