package co.edu.ufps.dto;

import co.edu.ufps.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String documento;

    public Cliente toEntity() {
        Cliente cliente = new Cliente();
        cliente.setId(this.id);
        cliente.setNombre(this.nombre);
        cliente.setDocumento(this.documento);
        return cliente;
    }
}
