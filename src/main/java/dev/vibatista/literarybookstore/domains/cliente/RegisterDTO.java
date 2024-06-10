package dev.vibatista.literarybookstore.domains.cliente;

public record RegisterDTO(String login, String password, ClienteRoles role) {
}
