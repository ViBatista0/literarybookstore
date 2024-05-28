package dev.vibatista.literarybookstore.repositories.cliente;

import dev.vibatista.literarybookstore.domains.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}
