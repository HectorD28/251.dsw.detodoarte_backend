package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.ExposicionVirtualObraRequest;
import dsw.detodoartebackend.dto.ExposicionVirtualObraResponse;
import dsw.detodoartebackend.entity.ExposicionVirtualObra;
import dsw.detodoartebackend.entity.ExposicionVirtual;
import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.repository.ExposicionVirtualObraRepository;
import dsw.detodoartebackend.repository.ExposicionVirtualRepository;
import dsw.detodoartebackend.repository.ObraDeArteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExposicionVirtualObraService {

    @Autowired
    private ExposicionVirtualObraRepository exposicionVirtualObraRepository;

    @Autowired
    private ExposicionVirtualRepository exposicionVirtualRepository;

    @Autowired
    private ObraDeArteRepository obraDeArteRepository;

    // Obtener todas las exposiciones virtuales con obras asociadas
    public List<ExposicionVirtualObraResponse> getAllExposicionesConObras() {
        List<ExposicionVirtualObra> exposicionesObras = exposicionVirtualObraRepository.findAll();
        return ExposicionVirtualObraResponse.fromEntities(exposicionesObras);
    }

    // Crear una nueva asociación entre exposición virtual y obra
    public ExposicionVirtualObraResponse createExposicionVirtualObra(ExposicionVirtualObraRequest request) {
        ExposicionVirtual exposicionVirtual = exposicionVirtualRepository.findById(request.getIdExposicionVirtual())
                .orElseThrow(() -> new RuntimeException("Exposición virtual no encontrada"));

        ObraDeArte obra = obraDeArteRepository.findById(request.getIdObra())
                .orElseThrow(() -> new RuntimeException("Obra no encontrada"));

        ExposicionVirtualObra exposicionVirtualObra = ExposicionVirtualObra.builder()
                .exposicionVirtual(exposicionVirtual)
                .obra(obra)
                .build();

        ExposicionVirtualObra savedExposicionVirtualObra = exposicionVirtualObraRepository.save(exposicionVirtualObra);
        return ExposicionVirtualObraResponse.fromEntity(savedExposicionVirtualObra);
    }

    // Eliminar una asociación entre exposición virtual y obra
    public void deleteExposicionVirtualObra(Long id) {
        ExposicionVirtualObra exposicionVirtualObra = exposicionVirtualObraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asociación no encontrada con ID " + id));
        exposicionVirtualObraRepository.delete(exposicionVirtualObra);
    }
}
