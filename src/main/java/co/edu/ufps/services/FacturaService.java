package co.edu.ufps.services;

import co.edu.ufps.dto.FacturaDTO;
import co.edu.ufps.dto.MedioPagoDTO; // Añadir el import para MedioPagoDTO
import co.edu.ufps.entity.*;
import co.edu.ufps.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {
    private final ClienteRepository clienteRepository;
    private final TiendaRepository tiendaRepository;
    private final ProductoRepository productoRepository;
    private final CompraRepository compraRepository;
    private final FacturaRepository facturaRepository;
    private final TipoPagoRepository tipoPagoRepository;
    private final PagoRepository pagoRepository;

    public FacturaService(
            ClienteRepository clienteRepository,
            TiendaRepository tiendaRepository,
            ProductoRepository productoRepository,
            CompraRepository compraRepository,
            FacturaRepository facturaRepository,
            TipoPagoRepository tipoPagoRepository,
            PagoRepository pagoRepository) {
        this.clienteRepository = clienteRepository;
        this.tiendaRepository = tiendaRepository;
        this.productoRepository = productoRepository;
        this.compraRepository = compraRepository;
        this.facturaRepository = facturaRepository;
        this.tipoPagoRepository = tipoPagoRepository;
        this.pagoRepository = pagoRepository;
    }

    @Transactional
    public FacturaDTO procesarFactura(FacturaDTO facturaRequest, String tiendaUuid) {
        // 1. Validar que la tienda existe
        Optional<Tienda> tiendaOpt = tiendaRepository.findByUuid(tiendaUuid);
        if (!tiendaOpt.isPresent()) {
            throw new RuntimeException("La tienda no existe");
        }
        Tienda tienda = tiendaOpt.get();

        // 2. Validar que el cliente existe
        Optional<Cliente> clienteOpt = clienteRepository.findByDocumento(facturaRequest.getCliente().getDocumento());
        if (!clienteOpt.isPresent()) {
            throw new RuntimeException("El cliente no existe");
        }
        Cliente cliente = clienteOpt.get();

        // 3. Crear la compra
        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setTienda(tienda);
        compra.setFecha(java.sql.Date.valueOf(java.time.LocalDate.now())); // Conversión de LocalDate a Date
        compra.setTotal(calcularTotal(facturaRequest));
        compra.setImpuestos(facturaRequest.getImpuesto());
        compraRepository.save(compra);

        // 4. Procesar productos
        List<Producto> productos = procesarProductos(facturaRequest.getProductos());
        
        // 5. Asociar detalles de compra
        for (Producto producto : productos) {
            DetallesCompra detallesCompra = new DetallesCompra();
            detallesCompra.setCompra(compra);
            detallesCompra.setProducto(producto);
            detallesCompra.setCantidad(facturaRequest.getProductos().get(0).getCantidad());
            detallesCompra.setPrecio(producto.getPrecio());
            detallesCompra.setDescuento(0); // Esto lo puedes ajustar
            // Guardar detallesCompra en la base de datos
        }

        // 6. Crear pagos y asociarlos con la compra
        procesarPagos(facturaRequest.getMediosPago(), compra);

        // 7. Crear la factura
        Factura factura = new Factura();
        factura.setCompra(compra);
        factura.setNumero("12"); // Aquí deberías generar un número único para la factura
        facturaRepository.save(factura);

        // 8. Crear y retornar la respuesta
        FacturaDTO respuesta = new FacturaDTO();
        respuesta.setNumero("12");
        respuesta.setTotal(compra.getTotal());
        respuesta.setFecha(compra.getFecha().toString());
        return respuesta;
    }

    private List<Producto> procesarProductos(List<ProductoDTO> productosRequest) {
        // Aquí procesas los productos en la factura
        // Este método buscaría los productos por su referencia y los devolvería en una lista
        // Si algún producto no existe, podrías lanzar un error
        // Ejemplo:
        return productosRequest.stream()
                .map(p -> productoRepository.findByReferencia(p.getReferencia()).orElseThrow(() -> new RuntimeException("Producto no encontrado")))
                .toList();
    }

    private void procesarPagos(List<MedioPagoDTO> mediosPagoRequest, Compra compra) {
        // Aquí procesas los pagos que se asocian a la compra
        for (MedioPagoDTO pagoRequest : mediosPagoRequest) {
            TipoPago tipoPago = tipoPagoRepository.findByNombre(pagoRequest.getTipoPago())
                    .orElseThrow(() -> new RuntimeException("Tipo de pago no encontrado"));
            
            Pago pago = new Pago();
            pago.setCompra(compra);
            pago.setTipoPago(tipoPago);
            pago.setValor(pagoRequest.getValor());
            pago.setTipoTarjeta(pagoRequest.getTipoTarjeta());
            pago.setCuotas(pagoRequest.getCuotas());
            pagoRepository.save(pago);
        }
    }

    private double calcularTotal(FacturaDTO facturaRequest) {
        // Aquí calculas el total de la factura teniendo en cuenta los productos y descuentos
        // Por ejemplo, sumando los precios de los productos y aplicando el descuento
        double total = 0;
        for (ProductoDTO productoDTO : facturaRequest.getProductos()) {
            Optional<Producto> producto = productoRepository.findByReferencia(productoDTO.getReferencia());
            if (producto.isPresent()) {
                double precioConDescuento = producto.get().getPrecio() * (1 - productoDTO.getDescuento() / 100);
                total += precioConDescuento * productoDTO.getCantidad();
            }
        }
        total += (total * facturaRequest.getImpuesto()) / 100;
        return total;
    }
}
