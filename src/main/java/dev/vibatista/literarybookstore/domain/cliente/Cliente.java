package dev.vibatista.literarybookstore.domain.cliente;

import dev.vibatista.literarybookstore.domain.livro.Livro;
import dev.vibatista.literarybookstore.domain.pedido.Pedido;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
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

    private List<Pedido> pedidoList;

    private List<Livro> listaDesejos;


}
