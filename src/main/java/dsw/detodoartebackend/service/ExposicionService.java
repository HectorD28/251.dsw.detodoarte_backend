package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.ExposicionRequest;
import dsw.detodoartebackend.entity.Exposicion;
import dsw.detodoartebackend.entity.SolicitudExposicion;
import dsw.detodoartebackend.repository.ExposicionRepository;
import dsw.detodoartebackend.repository.SolicitudExposicionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExposicionService {

    @Autowired
    private ExposicionRepository exposicionRepository;

    @Autowired
    private SolicitudExposicionRepository solicitudExposicionRepository;

    public Exposicion programarExposicion(ExposicionRequest request) {
        SolicitudExposicion solicitud = solicitudExposicionRepository.findById(request.getIdSolicitudExposicion())
            .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        Exposicion exposicion = Exposicion.builder()
            .nombre(request.getNombre())
            .descripcion(request.getDescripcion())
            .fecha_inicio(request.getFecha_inicio())
            .fecha_fin(request.getFecha_fin())
            .tipo_exposicion(request.getTipo_exposicion())
            .solicitudExposicion(solicitud)
            .build();

        return exposicionRepository.save(exposicion);
    }
}