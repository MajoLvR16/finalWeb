package co.edu.ufps.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tienda")
public class Tienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String direccion;

    @Column(nullable = false, unique = true)
    private String uuid;
}
