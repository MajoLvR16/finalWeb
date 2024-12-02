package co.edu.ufps.services;

import co.edu.ufps.dto.ProductoDTO;
import co.edu.ufps.entity.Producto;
import co.edu.ufps.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoDTO> listarProductos() {
        return productoRepository.findAll().stream()
                .map(producto -> new ProductoDTO(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getCantidad()))
                .collect(Collectors.toList());
    }

    public void guardarProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setCantidad(productoDTO.getCantidad());
        productoRepository.save(producto);
    }
}
