package dev.vibatista.literarybookstore.domain.avaliacao;

import dev.vibatista.literarybookstore.domain.cliente.Cliente;
import dev.vibatista.literarybookstore.domain.livro.Livro;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private Livro livro;

    private Cliente cliente;

    private Short nota;

    private String comentario;

    private LocalDate dataAvaliacao;

}
