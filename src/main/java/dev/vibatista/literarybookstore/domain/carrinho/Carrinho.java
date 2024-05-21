package dev.vibatista.literarybookstore.domain.carrinho;

import dev.vibatista.literarybookstore.domain.cliente.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "carrinho")
public class Carrinho {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private Cliente cliente;

    private List<ItemCarrinho> itensCarrinho;

}
