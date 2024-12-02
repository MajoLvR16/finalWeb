package co.edu.ufps.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private BigDecimal total;
    private LocalDateTime fecha;
    private BigDecimal impuesto;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    private String cajeroToken;

    @ManyToOne
    @JoinColumn(name = "tienda_id")
    private Tienda tienda;
}
