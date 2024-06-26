package dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente;

import dev.vibatista.literarybookstore.domain.models.cliente.ClienteRoles;

import java.time.LocalDate;
import java.util.UUID;

public record ClienteDTO(UUID id, String nome, String email, String senha, ClienteRoles role,
                         String endereco, String telefone, LocalDate dataRegistro)
{
    public UUID getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getEmail(){
        return email;
    }

}

