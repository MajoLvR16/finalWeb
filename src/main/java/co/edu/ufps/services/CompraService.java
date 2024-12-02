package co.edu.ufps.services;

import co.edu.ufps.dto.FacturaDTO;
import co.edu.ufps.entity.Compra;
import co.edu.ufps.entity.Tienda;
import co.edu.ufps.repository.CompraRepository;
import co.edu.ufps.repository.TiendaRepository;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final TiendaRepository tiendaRepository;

    public CompraService(CompraRepository compraRepository, TiendaRepository tiendaRepository) {
        this.compraRepository = compraRepository;
        this.tiendaRepository = tiendaRepository;
    }

    public void procesarCompra(FacturaDTO facturaRequest, String tiendaUuid) {
        // Buscar la tienda por UUID y manejar el Optional
        Tienda tienda = tiendaRepository.findByUuid(tiendaUuid)
                .orElseThrow(() -> new IllegalArgumentException("Tienda no encontrada con el UUID: " + tiendaUuid));

        // Crear la entidad de compra
        Compra compra = new Compra();
        compra.setCliente(facturaRequest.getCliente().toEntity()); // Convertir ClienteDTO a Cliente
        compra.setTienda(tienda); // Usar la tienda obtenida
        compra.setTotal(facturaRequest.getTotal()); // Establecer el total calculado
        compra.setImpuestos(facturaRequest.getImpuesto()); // Establecer el impuesto

        // Guardar la compra en la base de datos
        compraRepository.save(compra);
    }
}
