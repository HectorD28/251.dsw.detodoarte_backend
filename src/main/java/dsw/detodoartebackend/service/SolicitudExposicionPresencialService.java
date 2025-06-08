package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.SolicitudExposicionPresencialRequest;
import dsw.detodoartebackend.dto.SolicitudExposicionPresencialResponse;
import dsw.detodoartebackend.entity.SolicitudExposicionPresencial;
import dsw.detodoartebackend.entity.Artista;
import dsw.detodoartebackend.repository.SolicitudExposicionPresencialRepository;
import dsw.detodoartebackend.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudExposicionPresencialService {

    @Autowired
    private SolicitudExposicionPresencialRepository solicitudRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    public List<SolicitudExposicionPresencialResponse> getAllSolicitudes() {
        List<SolicitudExposicionPresencial> solicitudes = solicitudRepository.findAll();
        return SolicitudExposicionPresencialResponse.fromEntities(solicitudes);
    }

    public SolicitudExposicionPresencialResponse getSolicitudById(Long id) {
        SolicitudExposicionPresencial solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con ID " + id));
        return SolicitudExposicionPresencialResponse.fromEntity(solicitud);
    }

    public SolicitudExposicionPresencialResponse createSolicitud(SolicitudExposicionPresencialRequest request) {
        Artista artista = artistaRepository.findById(request.getIdArtista())
                .orElseThrow(() -> new RuntimeException("Artista no encontrado"));

        SolicitudExposicionPresencial solicitud = SolicitudExposicionPresencial.builder()
                .artista(artista)
                .fechaEmisionSolicitud(request.getFechaEmisionSolicitud())
                .estadoSolicitud(request.getEstadoSolicitud())
                .comentarios(request.getComentarios())
                .fechaRecepcionSolicitud(request.getFechaRecepcionSolicitud())
                .build();

        SolicitudExposicionPresencial savedSolicitud = solicitudRepository.save(solicitud);
        return SolicitudExposicionPresencialResponse.fromEntity(savedSolicitud);
    }

    public SolicitudExposicionPresencialResponse updateSolicitud(Long id, SolicitudExposicionPresencialRequest request) {
        SolicitudExposicionPresencial existingSolicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con ID " + id));

        Artista artista = artistaRepository.findById(request.getIdArtista())
                .orElseThrow(() -> new RuntimeException("Artista no encontrado"));

        existingSolicitud.setArtista(artista);
        existingSolicitud.setFechaEmisionSolicitud(request.getFechaEmisionSolicitud());
        existingSolicitud.setEstadoSolicitud(request.getEstadoSolicitud());
        existingSolicitud.setComentarios(request.getComentarios());
        existingSolicitud.setFechaRecepcionSolicitud(request.getFechaRecepcionSolicitud());

        SolicitudExposicionPresencial updatedSolicitud = solicitudRepository.save(existingSolicitud);
        return SolicitudExposicionPresencialResponse.fromEntity(updatedSolicitud);
    }

    public void deleteSolicitud(Long id) {
        SolicitudExposicionPresencial solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con ID " + id));
        solicitudRepository.delete(solicitud);
    }
}
