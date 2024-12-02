package co.edu.ufps.repository;

import co.edu.ufps.entity.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TiendaRepository extends JpaRepository<Tienda, Long> {
    Optional<Tienda> findByUuid(String uuid);
}
