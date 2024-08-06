package dev.vibatista.literarybookstore.domain.services.cliente;

import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import dev.vibatista.literarybookstore.domain.repositories.cliente.ClienteRepository;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente.CadastroClienteDTO;
import dev.vibatista.literarybookstore.domain.mappers.ClienteMapper;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente.EditarClienteDTO;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente.ListarClientesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    /*
     * Cria um cliente, o repository chama o método save do JPARepository, e faz isso para os demais métodos, com
     * seus respectivos métodos.
     * */
    public Cliente criarCliente(CadastroClienteDTO cadastroClienteDTO) {

        Cliente clienteByEmail = clienteRepository.findByEmail(cadastroClienteDTO.getEmail());
        Cliente clienteByCPF = clienteRepository.findByCpf(cadastroClienteDTO.getCpf());

        if (clienteByEmail != null || clienteByCPF != null)
            throw new IllegalArgumentException("Já existe um cliente cadastrado com essas informações!");

        Cliente cliente = new Cliente(cadastroClienteDTO);
        return clienteRepository.save(cliente);
    }

    public List<ListarClientesDTO> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(ListarClientesDTO::fromCliente)
                .collect(Collectors.toList());
    }

    public Cliente editarCliente(UUID id, EditarClienteDTO editarClienteDTO) {
        Optional<Cliente> clienteAntigo = Optional.ofNullable(clienteRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Nenhum cliente encontrado com esse id!")));

        Cliente cliente = clienteAntigo.get();

        cliente.setNome(Optional.ofNullable(editarClienteDTO.nome()).orElse(cliente.getNome()));
        cliente.setEmail(Optional.ofNullable(editarClienteDTO.email()).orElse(cliente.getEmail()));
        cliente.setEndereco(Optional.ofNullable(editarClienteDTO.endereco()).orElse(cliente.getEndereco()));
        cliente.setTelefone(Optional.ofNullable(editarClienteDTO.telefone()).orElse(cliente.getTelefone()));

        return clienteRepository.save(cliente);

    }

    public void delete(UUID id) {

        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty())
            throw new NullPointerException("Cliente não encontrado.");

        clienteRepository.deleteById(id);
    }

    // O Optional indica que pode não existir um cliente com esse ID
    public Optional<Cliente> findById(UUID id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) throw new NullPointerException("Não foi encontrado nenhum cliente com esse id!");

        return cliente;
    }

    public Cliente findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public Cliente findByEmail(String cpf) {
        return clienteRepository.findByEmail(cpf);
    }


}
