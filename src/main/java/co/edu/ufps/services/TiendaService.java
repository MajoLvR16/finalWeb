package co.edu.ufps.services;

import co.edu.ufps.dto.TiendaDTO;
import co.edu.ufps.entity.Tienda;
import co.edu.ufps.repository.TiendaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TiendaService {

    private final TiendaRepository tiendaRepository;

    // Constructor para inyección de dependencias
    public TiendaService(TiendaRepository tiendaRepository) {
        this.tiendaRepository = tiendaRepository;
    }

    // Método para registrar una nueva tienda
    public TiendaDTO registrarTienda(TiendaDTO tiendaDTO) {
        Tienda tienda = new Tienda();
        tienda.setNombre(tiendaDTO.getNombre());
        tienda.setDireccion(tiendaDTO.getDireccion());
        tienda.setUuid(tiendaDTO.getUuid());

        Tienda tiendaGuardada = tiendaRepository.save(tienda);

        // Convertir la tienda guardada a TiendaDTO y devolver
        return new TiendaDTO(
            tiendaGuardada.getId(),
            tiendaGuardada.getNombre(),
            tiendaGuardada.getDireccion(),
            tiendaGuardada.getUuid()
        );
    }

    // Método para obtener una tienda por su UUID
    public TiendaDTO obtenerTiendaPorUuid(String uuid) {
        Optional<Tienda> tiendaOpt = tiendaRepository.findByUuid(uuid);
        if (tiendaOpt.isEmpty()) {
            throw new IllegalArgumentException("Tienda no encontrada con el UUID: " + uuid);
        }

        Tienda tienda = tiendaOpt.get();

        // Convertir la entidad Tienda a TiendaDTO
        return new TiendaDTO(
            tienda.getId(),
            tienda.getNombre(),
            tienda.getDireccion(),
            tienda.getUuid()
        );
    }

    // Método para actualizar los datos de una tienda
    public TiendaDTO actualizarTienda(Long id, TiendaDTO tiendaDTO) {
        Optional<Tienda> tiendaOpt = tiendaRepository.findById(id);
        if (tiendaOpt.isEmpty()) {
            throw new IllegalArgumentException("Tienda no encontrada con el ID: " + id);
        }

        Tienda tienda = tiendaOpt.get();
        tienda.setNombre(tiendaDTO.getNombre());
        tienda.setDireccion(tiendaDTO.getDireccion());
        tienda.setUuid(tiendaDTO.getUuid());

        Tienda tiendaActualizada = tiendaRepository.save(tienda);

        // Convertir la tienda actualizada a TiendaDTO y devolver
        return new TiendaDTO(
            tiendaActualizada.getId(),
            tiendaActualizada.getNombre(),
            tiendaActualizada.getDireccion(),
            tiendaActualizada.getUuid()
        );
    }

    // Método para eliminar una tienda por su ID
    public void eliminarTienda(Long id) {
        if (!tiendaRepository.existsById(id)) {
            throw new IllegalArgumentException("Tienda no encontrada con el ID: " + id);
        }

        tiendaRepository.deleteById(id);
    }
}
