package co.edu.ufps.controller;

import co.edu.ufps.dto.FacturaDTO;
import co.edu.ufps.services.CompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping("/{tiendaUuid}")
    public ResponseEntity<String> procesarCompra(@PathVariable String tiendaUuid, @RequestBody FacturaDTO facturaDTO) {
        compraService.procesarCompra(facturaDTO, tiendaUuid);  // Aquí llamas al método procesarCompra
        return ResponseEntity.ok("Compra procesada exitosamente");
    }
}
