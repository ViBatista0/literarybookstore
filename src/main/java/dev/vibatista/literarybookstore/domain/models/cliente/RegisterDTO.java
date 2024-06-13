package dev.vibatista.literarybookstore.domain.models.cliente;

public record RegisterDTO(String login, String password, ClienteRoles role) {
}
