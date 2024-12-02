package co.edu.ufps.controller;

import co.edu.ufps.services.FacturaService;
import co.edu.ufps.dto.FacturaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crear")
public class FacturaController {
    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @PostMapping("/{tiendaUuid}")
    public ResponseEntity<String> registrarFactura(@PathVariable String tiendaUuid, @RequestBody FacturaDTO facturaDTO) {
        facturaService.procesarFactura(facturaDTO, tiendaUuid);
        return ResponseEntity.ok("Factura registrada exitosamente");
    }
}
