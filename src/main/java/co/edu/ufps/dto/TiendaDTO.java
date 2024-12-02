package co.edu.ufps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiendaDTO {
    
    private Long id;
    private String nombre;
    private String direccion;
    private String uuid;
}
