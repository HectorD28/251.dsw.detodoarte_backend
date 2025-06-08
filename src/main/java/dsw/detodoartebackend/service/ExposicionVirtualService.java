package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.ExposicionVirtualRequest;
import dsw.detodoartebackend.dto.ExposicionVirtualResponse;
import dsw.detodoartebackend.entity.ExposicionVirtual;
import dsw.detodoartebackend.repository.ExposicionVirtualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExposicionVirtualService {

    @Autowired
    private ExposicionVirtualRepository exposicionVirtualRepository;

    // Obtener todas las exposiciones virtuales
    public List<ExposicionVirtualResponse> getAllExposiciones() {
        List<ExposicionVirtual> exposiciones = exposicionVirtualRepository.findAll();
        return ExposicionVirtualResponse.fromEntities(exposiciones);
    }

    // Obtener exposición virtual por ID
    public ExposicionVirtualResponse getExposicionById(Long id) {
        ExposicionVirtual exposicion = exposicionVirtualRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exposición virtual no encontrada con ID " + id));
        return ExposicionVirtualResponse.fromEntity(exposicion);
    }

    // Crear nueva exposición virtual
    public ExposicionVirtualResponse createExposicion(ExposicionVirtualRequest request) {
        ExposicionVirtual exposicion = ExposicionVirtual.builder()
                .fechaPublicacion(request.getFechaPublicacion())
                .estadoPublicacion(request.getEstadoPublicacion())
                .urlPublicacion(request.getUrlPublicacion())
                .comentarios(request.getComentarios())
                .build();

        ExposicionVirtual savedExposicion = exposicionVirtualRepository.save(exposicion);
        return ExposicionVirtualResponse.fromEntity(savedExposicion);
    }

    // Actualizar exposición virtual
    public ExposicionVirtualResponse updateExposicion(Long id, ExposicionVirtualRequest request) {
        ExposicionVirtual existingExposicion = exposicionVirtualRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exposición virtual no encontrada con ID " + id));

        existingExposicion.setFechaPublicacion(request.getFechaPublicacion());
        existingExposicion.setEstadoPublicacion(request.getEstadoPublicacion());
        existingExposicion.setUrlPublicacion(request.getUrlPublicacion());
        existingExposicion.setComentarios(request.getComentarios());

        ExposicionVirtual updatedExposicion = exposicionVirtualRepository.save(existingExposicion);
        return ExposicionVirtualResponse.fromEntity(updatedExposicion);
    }

    // Eliminar exposición virtual
    public void deleteExposicion(Long id) {
        ExposicionVirtual exposicion = exposicionVirtualRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exposición virtual no encontrada con ID " + id));
        exposicionVirtualRepository.delete(exposicion);
    }
}
