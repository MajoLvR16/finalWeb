package co.edu.ufps.controller;

import co.edu.ufps.dto.TiendaDTO;
import co.edu.ufps.services.TiendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tiendas")
public class TiendaController {

    private final TiendaService tiendaService;

    public TiendaController(TiendaService tiendaService) {
        this.tiendaService = tiendaService;
    }

    // Endpoint para registrar una tienda
    @PostMapping
    public ResponseEntity<TiendaDTO> registrarTienda(@RequestBody TiendaDTO tiendaDTO) {
        TiendaDTO tiendaGuardada = tiendaService.registrarTienda(tiendaDTO);
        return ResponseEntity.ok(tiendaGuardada);
    }

    // Endpoint para obtener una tienda por su UUID
    @GetMapping("/{uuid}")
    public ResponseEntity<TiendaDTO> obtenerTiendaPorUuid(@PathVariable String uuid) {
        TiendaDTO tienda = tiendaService.obtenerTiendaPorUuid(uuid);
        return ResponseEntity.ok(tienda);
    }

    // Endpoint para actualizar una tienda
    @PutMapping("/{id}")
    public ResponseEntity<TiendaDTO> actualizarTienda(@PathVariable Long id, @RequestBody TiendaDTO tiendaDTO) {
        TiendaDTO tiendaActualizada = tiendaService.actualizarTienda(id, tiendaDTO);
        return ResponseEntity.ok(tiendaActualizada);
    }

    // Endpoint para eliminar una tienda
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTienda(@PathVariable Long id) {
        tiendaService.eliminarTienda(id);
        return ResponseEntity.ok("Tienda eliminada exitosamente");
    }
}
