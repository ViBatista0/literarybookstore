package dev.vibatista.literarybookstore.domain.models.cliente;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.vibatista.literarybookstore.domain.models.livro.Livro;
import dev.vibatista.literarybookstore.domain.models.pedido.Pedido;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente.CadastroClienteDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name = "clientes")
@Table(name = "clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "clienteId")
/*
 Para classes que representam um user no bd, temos que implementar a interface UserDetails, que possui vários métodos
 de utilidade para autenticação.
 */
public class Cliente implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "clienteId", nullable = false)
    private UUID clienteId;

    @NotBlank(message = "O nome do cliente não pode ser vazio!")
    private String nome;

    @NotBlank(message = "O email do cliente não pode ser vazio!")
    private String email;

    private String senha;

    @NotBlank(message = "O CPF deve ser informado!")
    private String cpf;

    private ClienteRoles roles;

    private String endereco;

    private String telefone;

    private LocalDate dataRegistro;
    // Essa anotação vai ignorar esse lado do relacionamento na (des)serialização JSON
    @JsonBackReference
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidoList;

    public Cliente(CadastroClienteDTO cadastroClienteDTO){
        this.nome = cadastroClienteDTO.getNome();
        this.email = cadastroClienteDTO.getEmail();
        this.cpf = cadastroClienteDTO.getCpf();
        this.roles = ClienteRoles.USER;
        this.endereco = cadastroClienteDTO.getEndereco();
        this.telefone = cadastroClienteDTO.getTelefone();
        this.dataRegistro = LocalDate.now();
    }

//    public Cliente(String email, String password, ClienteRoles role){
//        this.email = email;
//        this.senha = password;
//        this.roles = role;
//        this.dataRegistro = LocalDate.now();
//    }

    /*
     *   Vai pegar a hierarquia do usuário, com base na role que criamos no enum ClientesRoles.
     *   Se for ADMIN, retorna as permissões do ROLE_USER (nativo), e do ROLE_ADMIN, ou seja, permissão máxima.
     *   Caso o oposto, é um usuário normal, com permissões normais.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.roles == ClienteRoles.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
