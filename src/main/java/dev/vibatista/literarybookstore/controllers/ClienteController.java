package dev.vibatista.literarybookstore.controllers;

import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import dev.vibatista.literarybookstore.domain.services.cliente.ClienteService;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente.ClienteDTO;
import dev.vibatista.literarybookstore.util.UUIDUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    /*
     * A Anotação PostMapping, como o nome diz, esse método vai ser chamado quando houver um POST no "/clientes"
     * Retorno ResponseEntity, é para enviar cabeçalhos HTTP (200, 201, 404...).
     * */
    @PostMapping
    public ResponseEntity<?> criarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {

        Cliente clienteByCpf = service.findByCpf(clienteDTO.getCpf());
        Cliente clienteByEmail = service.findByEmail(clienteDTO.getEmail());

        if (clienteByCpf != null || clienteByEmail != null)
            return ResponseEntity.badRequest().body("Não pode criar dois clientes com o mesmo CPF ou email!");

        if (clienteDTO.getNome() == null || clienteDTO.getNome().isEmpty())
            return ResponseEntity.badRequest().body("O nome do cliente não pode ser vazio!");

        if (clienteDTO.getEmail() == null || clienteDTO.getEmail().isEmpty())
            return ResponseEntity.badRequest().body("O email do cliente não pode ser vazio!");

        if (clienteDTO.getCpf() == null || clienteDTO.getCpf().isEmpty())
            return ResponseEntity.badRequest().body("O CPF do cliente não pode ser vazio!");

        URI uri = URI.create("/clientes/" + clienteDTO.getId());
        service.create(clienteDTO);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<?> listarClientes() {
        List<Cliente> clientes = service.listarClientes();
        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        Optional<Cliente> cliente = service.findById(UUID.fromString(id));

        if (cliente.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum cliente com esse id!");

        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable UUID id) {

        Optional<Cliente> cliente = service.findById(id);

        if (cliente.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");

        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao excluir cliente: " + ex);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarCliente(@PathVariable String id, @RequestBody ClienteDTO clienteDTO) {
        if (id == null || id.isEmpty())
            return ResponseEntity.badRequest().body("Informe o ID do cliente.");

        if (clienteDTO == null)
            return ResponseEntity.badRequest().body("Informe os dados cliente.");

        Optional<Cliente> cliente = service.findById(clienteDTO.getId());

        if (cliente.isEmpty())
            return ResponseEntity.badRequest().body("Não foi encontrado nenhum cliente com esse id!");
        else {
            service.editarCliente(UUID.fromString(id), clienteDTO);
            return ResponseEntity.ok().body("Cliente alterado com sucesso!");
        }

    }

}
