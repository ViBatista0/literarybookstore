package dev.vibatista.literarybookstore.domain.cliente;

import dev.vibatista.literarybookstore.domain.livro.Livro;
import dev.vibatista.literarybookstore.domain.pedido.Pedido;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String nome;

    private String email;

    private String senha;

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


}
