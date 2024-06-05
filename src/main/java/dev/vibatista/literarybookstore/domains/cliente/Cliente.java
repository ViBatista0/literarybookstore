package dev.vibatista.literarybookstore.domains.cliente;

import dev.vibatista.literarybookstore.domains.livro.Livro;
import dev.vibatista.literarybookstore.domains.pedido.Pedido;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name = "clientes")
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
/*
 Para classes que representam um user no bd, temos que implementar a interface UserDetails, que possui vários métodos
 de utilidade para autenticação.
 */
public class Cliente implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;

    private String nome;

    private String login;

    private String email;

    private String senha;

    private ClienteRoles roles;

    private String endereco;

    private String telefone;

    private LocalDate dataRegistro;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidoList;

    @ManyToMany
    @JoinTable(
            name = "cliente_livro",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "livro_id")
    )
    private List<Livro> listaDesejos = new ArrayList<>();

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
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
