package dev.vibatista.literarybookstore.domain.repositories.cliente;

import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    /*
     * Quando criamos um método que inicia com findBy, o Spring já entende que esse método vai ter que passar por
     * determinado atributo, e fazer essa checagem. Precisamos retornar um UserDetails, pois é a classe de manipulação
     * de auth do Spring.
     */
    Cliente findByEmail(String email);

    Cliente findByCpf(String cpf);
}
