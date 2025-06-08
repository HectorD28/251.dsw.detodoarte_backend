package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.EspacioGaleriaRequest;
import dsw.detodoartebackend.dto.EspacioGaleriaResponse;
import dsw.detodoartebackend.entity.EspacioGaleria;
import dsw.detodoartebackend.repository.EspacioGaleriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspacioGaleriaService {

    @Autowired
    private EspacioGaleriaRepository espacioGaleriaRepository;

    public List<EspacioGaleriaResponse> getAllEspacios() {
        List<EspacioGaleria> espacios = espacioGaleriaRepository.findAll();
        return EspacioGaleriaResponse.fromEntities(espacios);
    }

    public EspacioGaleriaResponse getEspacioById(Long id) {
        EspacioGaleria espacio = espacioGaleriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espacio no encontrado con ID " + id));
        return EspacioGaleriaResponse.fromEntity(espacio);
    }

    public EspacioGaleriaResponse createEspacio(EspacioGaleriaRequest request) {
        EspacioGaleria espacio = EspacioGaleria.builder()
                .nombreEspacio(request.getNombreEspacio())
                .descripcion(request.getDescripcion())
                .build();

        EspacioGaleria savedEspacio = espacioGaleriaRepository.save(espacio);
        return EspacioGaleriaResponse.fromEntity(savedEspacio);
    }

    public EspacioGaleriaResponse updateEspacio(Long id, EspacioGaleriaRequest request) {
        EspacioGaleria existingEspacio = espacioGaleriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espacio no encontrado con ID " + id));

        existingEspacio.setNombreEspacio(request.getNombreEspacio());
        existingEspacio.setDescripcion(request.getDescripcion());

        EspacioGaleria updatedEspacio = espacioGaleriaRepository.save(existingEspacio);
        return EspacioGaleriaResponse.fromEntity(updatedEspacio);
    }

    public void deleteEspacio(Long id) {
        EspacioGaleria espacio = espacioGaleriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espacio no encontrado con ID " + id));
        espacioGaleriaRepository.delete(espacio);
    }
}
