package co.edu.ufps.repository;

import co.edu.ufps.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByDocumento(String documento);
}
