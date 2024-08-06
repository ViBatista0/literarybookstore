package dev.vibatista.literarybookstore.controllers;


import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import dev.vibatista.literarybookstore.domain.services.cliente.ClienteService;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente.CadastroClienteDTO;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente.EditarClienteDTO;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente.ListarClientesDTO;
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
    public ResponseEntity<?> criarCliente(@Valid @RequestBody CadastroClienteDTO cadastroClienteDTO) {
        try {
            Cliente cliente = service.criarCliente(cadastroClienteDTO);
            URI uri = URI.create("/clientes/" + cliente.getClienteId());
            return ResponseEntity.created(uri).body(cliente);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ListarClientesDTO>> listarClientes() {
        List<ListarClientesDTO> clientesDTO = service.listarClientes();
        return ResponseEntity.ok().body(clientesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            Optional<Cliente> cliente = service.findById(UUID.fromString(id));
            return ResponseEntity.ok().body(cliente);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum cliente com esse id!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable UUID id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao excluir cliente: " + ex);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarCliente(@PathVariable String id, @RequestBody EditarClienteDTO editarClienteDTO) {
        try {
            Cliente cliente = service.editarCliente(UUID.fromString(id), editarClienteDTO);
            return ResponseEntity.ok().body(cliente);
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body("Erro ao editar o cliente: " + ex.getMessage());
        }

    }

}
