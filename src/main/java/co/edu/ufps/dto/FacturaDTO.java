package co.edu.ufps.dto;

import java.util.List;

public class FacturaDTO {
    private String numero;
    private ClienteDTO cliente;
    private List<ProductoDTO> productos;
    private double impuesto;
    private double total;
    private String fecha;
    private List<MedioPagoDTO> mediosPago;

    // Getters y setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<MedioPagoDTO> getMediosPago() {
        return mediosPago;
    }

    public void setMediosPago(List<MedioPagoDTO> mediosPago) {
        this.mediosPago = mediosPago;
    }
}
