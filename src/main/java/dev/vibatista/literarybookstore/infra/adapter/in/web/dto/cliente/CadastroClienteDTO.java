package dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente;

import java.time.LocalDate;
import java.util.UUID;

public record CadastroClienteDTO(
        UUID id,
        String nome,
        String email,
        String cpf,
        String senha,
        String endereco,
        String telefone,
        LocalDate dataRegistro) {

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco(){
        return endereco;
    }

    public String getTelefone(){
        return telefone;
    }

}

