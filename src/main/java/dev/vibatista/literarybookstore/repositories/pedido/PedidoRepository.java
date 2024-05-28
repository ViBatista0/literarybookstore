package dev.vibatista.literarybookstore.repositories.pedido;

import dev.vibatista.literarybookstore.domains.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
