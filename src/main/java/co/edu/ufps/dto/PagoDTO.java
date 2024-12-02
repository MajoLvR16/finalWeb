package co.edu.ufps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagoDTO {
    private String tipoPago;
    private String tipoTarjeta;
    private int cuotas;
    private double valor;
}
