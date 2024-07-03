package dev.vibatista.literarybookstore.domain.models.cliente;

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

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidoList;

    @ManyToMany
    @JoinTable(
            name = "cliente_livro",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "livro_id")
    )
    private List<Livro> listaDesejos = new ArrayList<>();

    public Cliente(CadastroClienteDTO cadastroClienteDTO){
        this.id = cadastroClienteDTO.getId();
        this.nome = cadastroClienteDTO.getNome();
        this.email = cadastroClienteDTO.getEmail();
        this.senha = cadastroClienteDTO.senha();
        this.cpf = cadastroClienteDTO.getCpf();
        this.endereco = cadastroClienteDTO.getEndereco();
        this.telefone = cadastroClienteDTO.getTelefone();
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ClienteRoles getRoles() {
        return roles;
    }

    public void setRoles(ClienteRoles roles) {
        this.roles = roles;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    public List<Livro> getListaDesejos() {
        return listaDesejos;
    }

    public void setListaDesejos(List<Livro> listaDesejos) {
        this.listaDesejos = listaDesejos;
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
