package co.edu.ufps.controller;

import co.edu.ufps.dto.ClienteDTO;
import co.edu.ufps.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodos() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody ClienteDTO clienteDTO) {
        clienteService.guardarCliente(clienteDTO);
        return ResponseEntity.ok("Cliente registrado exitosamente");
    }
}
