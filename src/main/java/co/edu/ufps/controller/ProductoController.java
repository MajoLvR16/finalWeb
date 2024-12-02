package co.edu.ufps.controller;

import co.edu.ufps.dto.ProductoDTO;
import co.edu.ufps.services.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerTodos() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody ProductoDTO productoDTO) {
        productoService.guardarProducto(productoDTO);
        return ResponseEntity.ok("Producto registrado exitosamente");
    }
}
