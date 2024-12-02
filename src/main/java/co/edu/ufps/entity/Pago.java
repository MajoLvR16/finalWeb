package co.edu.ufps.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "tipo_pago_id", nullable = false)
    private TipoPago tipoPago;

    private String tarjetaTipo; // Atributo con el nombre correcto

    private int cuotas;

    @Column(nullable = false)
    private double valor;

    // Los métodos setters y getters son generados por Lombok automáticamente
}
