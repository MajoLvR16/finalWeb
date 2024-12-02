package co.edu.ufps.services;

import co.edu.ufps.dto.ClienteDTO;
import co.edu.ufps.entity.Cliente;
import co.edu.ufps.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll().stream()
                .map(cliente -> new ClienteDTO(cliente.getId(), cliente.getNombre(), cliente.getDocumento()))
                .collect(Collectors.toList());
    }

    public void guardarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setDocumento(clienteDTO.getDocumento());
        clienteRepository.save(cliente);
    }
}
