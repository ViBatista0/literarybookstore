package dev.vibatista.literarybookstore.infra.adapter.in.web.dto.auth;

import dev.vibatista.literarybookstore.domain.models.cliente.ClienteRoles;

public record RegisterDTO(String login, String password, ClienteRoles role) {
}
