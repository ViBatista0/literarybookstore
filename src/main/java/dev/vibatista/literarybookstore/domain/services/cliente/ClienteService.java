package dev.vibatista.literarybookstore.domain.services.cliente;

import dev.vibatista.literarybookstore.domain.models.cliente.Cliente;
import dev.vibatista.literarybookstore.domain.models.livro.Livro;
import dev.vibatista.literarybookstore.domain.repositories.cliente.ClienteRepository;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente.CadastroClienteDTO;
import dev.vibatista.literarybookstore.domain.mappers.ClienteMapper;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente.EditarClienteDTO;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.cliente.ListarClientesDTO;
import dev.vibatista.literarybookstore.infra.adapter.in.web.dto.livro.EditarLivroDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Cliente create(CadastroClienteDTO cadastroClienteDTO) {
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
        Optional<Cliente> clienteAntigo = clienteRepository.findById(id);

        if (clienteAntigo.isEmpty())
            return null;

        Cliente cliente = clienteAntigo.get();

        cliente.setNome(editarClienteDTO.nome());
        cliente.setEmail(editarClienteDTO.email());
        cliente.setEndereco(editarClienteDTO.endereco());
        cliente.setTelefone(editarClienteDTO.telefone());

        return clienteRepository.save(cliente);

    }

    public void delete(UUID id) {
        clienteRepository.deleteById(id);
    }

    // O Optional indica que pode não existir um cliente com esse ID
    public Optional<Cliente> findById(UUID id) {
        return clienteRepository.findById(id);
    }

    public Cliente findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }
    public Cliente findByEmail(String cpf) {
        return clienteRepository.findByEmail(cpf);
    }


}
