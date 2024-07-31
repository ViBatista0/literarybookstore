package dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CadastroClienteDTO(
        @NotNull(message = "O nome não pode ser vazio!")
        String nome,
        @NotNull(message = "O email não pode ser vazio!")
        String email,
        @NotNull(message = "O CPF não pode ser vazio!")
        String cpf,
        @NotBlank(message = "O endereço não pode ser vazio!")
        String endereco,
        String telefone) {


    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

}

