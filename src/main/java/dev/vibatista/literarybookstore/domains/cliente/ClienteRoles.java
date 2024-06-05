package dev.vibatista.literarybookstore.domains.cliente;

public enum ClienteRoles {
    ADMIN("admin"),
    USER("user");

    private String role;

    ClienteRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

}
