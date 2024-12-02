package co.edu.ufps.repository;

import co.edu.ufps.entity.TipoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TipoPagoRepository extends JpaRepository<TipoPago, Long> {
    Optional<TipoPago> findByNombre(String nombre); // Devuelve un Optional
}
