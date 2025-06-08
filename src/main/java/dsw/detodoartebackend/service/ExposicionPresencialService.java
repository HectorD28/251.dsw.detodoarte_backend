package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.ExposicionPresencialRequest;
import dsw.detodoartebackend.dto.ExposicionPresencialResponse;
import dsw.detodoartebackend.entity.ExposicionPresencial;
import dsw.detodoartebackend.entity.SolicitudExposicionPresencial;
import dsw.detodoartebackend.entity.EspacioGaleria;
import dsw.detodoartebackend.repository.ExposicionPresencialRepository;
import dsw.detodoartebackend.repository.SolicitudExposicionPresencialRepository;
import dsw.detodoartebackend.repository.EspacioGaleriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExposicionPresencialService {

    @Autowired
    private ExposicionPresencialRepository exposicionRepository;

    @Autowired
    private SolicitudExposicionPresencialRepository solicitudExposicionPresencialRepository;

    @Autowired
    private EspacioGaleriaRepository espacioGaleriaRepository;

    public List<ExposicionPresencialResponse> getAllExposiciones() {
        List<ExposicionPresencial> exposiciones = exposicionRepository.findAll();
        return ExposicionPresencialResponse.fromEntities(exposiciones);
    }

    public ExposicionPresencialResponse getExposicionById(Long id) {
        ExposicionPresencial exposicion = exposicionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exposición no encontrada con ID " + id));
        return ExposicionPresencialResponse.fromEntity(exposicion);
    }

    public ExposicionPresencialResponse createExposicion(ExposicionPresencialRequest request) {
        SolicitudExposicionPresencial solicitud = solicitudExposicionPresencialRepository.findById(request.getIdSolicitudExposicionPresencial())
                .orElseThrow(() -> new RuntimeException("Solicitud de exposición no encontrada"));

        EspacioGaleria espacio = espacioGaleriaRepository.findById(request.getIdEspacioGaleria())
                .orElseThrow(() -> new RuntimeException("Espacio de galería no encontrado"));

        ExposicionPresencial exposicion = ExposicionPresencial.builder()
                .solicitudExposicionPresencial(solicitud)
                .espacioGaleria(espacio)
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .tipoExposicion(request.getTipoExposicion())
                .build();

        ExposicionPresencial savedExposicion = exposicionRepository.save(exposicion);
        return ExposicionPresencialResponse.fromEntity(savedExposicion);
    }

    public ExposicionPresencialResponse updateExposicion(Long id, ExposicionPresencialRequest request) {
        ExposicionPresencial existingExposicion = exposicionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exposición no encontrada con ID " + id));

        SolicitudExposicionPresencial solicitud = solicitudExposicionPresencialRepository.findById(request.getIdSolicitudExposicionPresencial())
                .orElseThrow(() -> new RuntimeException("Solicitud de exposición no encontrada"));

        EspacioGaleria espacio = espacioGaleriaRepository.findById(request.getIdEspacioGaleria())
                .orElseThrow(() -> new RuntimeException("Espacio de galería no encontrado"));

        existingExposicion.setSolicitudExposicionPresencial(solicitud);
        existingExposicion.setEspacioGaleria(espacio);
        existingExposicion.setFechaInicio(request.getFechaInicio());
        existingExposicion.setFechaFin(request.getFechaFin());
        existingExposicion.setTipoExposicion(request.getTipoExposicion());

        ExposicionPresencial updatedExposicion = exposicionRepository.save(existingExposicion);
        return ExposicionPresencialResponse.fromEntity(updatedExposicion);
    }

    public void deleteExposicion(Long id) {
        ExposicionPresencial exposicion = exposicionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exposición no encontrada con ID " + id));
        exposicionRepository.delete(exposicion);
    }
}
