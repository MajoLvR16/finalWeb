package co.edu.ufps.repository;

import co.edu.ufps.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    // Puedes agregar métodos personalizados si los necesitas
}
