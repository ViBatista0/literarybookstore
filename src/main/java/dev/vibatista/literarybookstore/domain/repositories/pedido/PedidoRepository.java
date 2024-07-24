package dev.vibatista.literarybookstore.domain.repositories.pedido;

import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import dev.vibatista.literarybookstore.domain.models.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    @Query("SELECT p FROM Pedido p WHERE p.cliente.clienteId = :clienteId")
    List<Pedido> findByClienteId(@Param("clienteId") UUID clienteId);
}
