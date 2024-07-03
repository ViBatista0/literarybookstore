package dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente;

import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import dev.vibatista.literarybookstore.domain.models.cliente.ClienteRoles;

import java.time.LocalDate;
import java.util.UUID;

public record ListarClientesDTO(UUID id, String nome, String email, String cpf, ClienteRoles role,
                                String endereco, String telefone, LocalDate dataRegistro) {

    public static ListarClientesDTO fromCliente(Cliente cliente) {
        return new ListarClientesDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getRoles(),
                cliente.getEndereco(),
                cliente.getTelefone(),
                cliente.getDataRegistro());
    }

}
