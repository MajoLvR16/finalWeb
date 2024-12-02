package co.edu.ufps.services;

import co.edu.ufps.entity.Pago;
import co.edu.ufps.entity.TipoPago;
import co.edu.ufps.repository.PagoRepository;
import co.edu.ufps.repository.TipoPagoRepository;
import org.springframework.stereotype.Service;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;
    private final TipoPagoRepository tipoPagoRepository;

    public PagoService(PagoRepository pagoRepository, TipoPagoRepository tipoPagoRepository) {
        this.pagoRepository = pagoRepository;
        this.tipoPagoRepository = tipoPagoRepository;
    }

    public Pago registrarPago(String tipoPago, String tipoTarjeta, int cuotas, double valor) {
        // Buscar el tipo de pago, usando Optional y orElseThrow si no se encuentra
        TipoPago tipo = tipoPagoRepository.findByNombre(tipoPago)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de pago no encontrado: " + tipoPago));

        Pago pago = new Pago();
        pago.setTipoPago(tipo); // Relacionamos el tipo de pago
        pago.setTarjetaTipo(tipoTarjeta); // Establecemos el tipo de tarjeta (nombre correcto del método)
        pago.setCuotas(cuotas); // Establecemos las cuotas
        pago.setValor(valor); // Establecemos el valor del pago

        return pagoRepository.save(pago); // Guardamos el pago en la base de datos
    }
}
