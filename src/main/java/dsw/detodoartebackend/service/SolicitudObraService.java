package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.SolicitudObraRequest;
import dsw.detodoartebackend.dto.SolicitudObraResponse;
import dsw.detodoartebackend.entity.SolicitudObra;
import dsw.detodoartebackend.entity.SolicitudExposicionPresencial;
import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.repository.SolicitudObraRepository;
import dsw.detodoartebackend.repository.SolicitudExposicionPresencialRepository;
import dsw.detodoartebackend.repository.ObraDeArteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudObraService {

    @Autowired
    private SolicitudObraRepository solicitudObraRepository;

    @Autowired
    private SolicitudExposicionPresencialRepository solicitudExposicionPresencialRepository;

    @Autowired
    private ObraDeArteRepository obraDeArteRepository;

    public List<SolicitudObraResponse> getAllSolicitudes() {
        List<SolicitudObra> solicitudes = solicitudObraRepository.findAll();
        return SolicitudObraResponse.fromEntities(solicitudes);
    }

    public SolicitudObraResponse getSolicitudById(Long id) {
        SolicitudObra solicitud = solicitudObraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con ID " + id));
        return SolicitudObraResponse.fromEntity(solicitud);
    }

    public SolicitudObraResponse createSolicitud(SolicitudObraRequest request) {
        SolicitudExposicionPresencial solicitudExposicion = solicitudExposicionPresencialRepository.findById(request.getIdSolicitudExposicionPresencial())
                .orElseThrow(() -> new RuntimeException("Solicitud de exposición no encontrada"));

        ObraDeArte obra = obraDeArteRepository.findById(request.getIdObra())
                .orElseThrow(() -> new RuntimeException("Obra no encontrada"));

        SolicitudObra solicitud = SolicitudObra.builder()
                .solicitudExposicionPresencial(solicitudExposicion)
                .obra(obra)
                .build();

        SolicitudObra savedSolicitud = solicitudObraRepository.save(solicitud);
        return SolicitudObraResponse.fromEntity(savedSolicitud);
    }

    public SolicitudObraResponse updateSolicitud(Long id, SolicitudObraRequest request) {
        SolicitudObra existingSolicitud = solicitudObraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con ID " + id));

        SolicitudExposicionPresencial solicitudExposicion = solicitudExposicionPresencialRepository.findById(request.getIdSolicitudExposicionPresencial())
                .orElseThrow(() -> new RuntimeException("Solicitud de exposición no encontrada"));

        ObraDeArte obra = obraDeArteRepository.findById(request.getIdObra())
                .orElseThrow(() -> new RuntimeException("Obra no encontrada"));

        existingSolicitud.setSolicitudExposicionPresencial(solicitudExposicion);
        existingSolicitud.setObra(obra);

        SolicitudObra updatedSolicitud = solicitudObraRepository.save(existingSolicitud);
        return SolicitudObraResponse.fromEntity(updatedSolicitud);
    }

    public void deleteSolicitud(Long id) {
        SolicitudObra solicitud = solicitudObraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con ID " + id));
        solicitudObraRepository.delete(solicitud);
    }
}
