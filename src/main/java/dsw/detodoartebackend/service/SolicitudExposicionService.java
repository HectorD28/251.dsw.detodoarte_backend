package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.SolicitudExposicionRequest;
import dsw.detodoartebackend.dto.SolicitudExposicionResponse;
import dsw.detodoartebackend.entity.Artista;
import dsw.detodoartebackend.entity.SolicitudExposicion;
import dsw.detodoartebackend.repository.ArtistaRepository;
import dsw.detodoartebackend.repository.SolicitudExposicionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitudExposicionService {

    @Autowired
    private SolicitudExposicionRepository solicitudexposicionRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    public List<SolicitudExposicionResponse> obtenerTodasSolicitudesExposicion() {
        return SolicitudExposicionResponse.fromEntities(solicitudexposicionRepository.findAll());
    }

    public SolicitudExposicionResponse guardarSolicitudExposicion(SolicitudExposicionRequest request) {

        Artista artista = artistaRepository.findById(request.getId_artista())
                .orElseThrow(() -> new RuntimeException("Artista no encontrado"));

        SolicitudExposicion solicitudexposicion = SolicitudExposicion.builder()
                .fechaEmisionSolicitud(request.getFecha_emision_solicitud())
                .estadoSolicitud(request.getEstado_solicitud())
                .comentarios(request.getComentarios())
                .artista(artista)
                .fechaRecepcionSolicitud(request.getFecha_recepcion_solicitud())
                .build();

        SolicitudExposicion saved = solicitudexposicionRepository.save(solicitudexposicion);
        return SolicitudExposicionResponse.fromEntity(saved);
    }

    public SolicitudExposicionResponse actualizarSolicitudExposicion(Long id, SolicitudExposicionRequest request) {
        SolicitudExposicion solicitudexposicionExistente = solicitudexposicionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud de exposicion no encontrada con ID " + id));

        Artista artista = artistaRepository.findById(request.getId_artista())
                .orElseThrow(() -> new RuntimeException("Artista no encontrado"));

        // Actualizamos campos
        solicitudexposicionExistente.setFechaEmisionSolicitud(request.getFecha_emision_solicitud());
        solicitudexposicionExistente.setEstadoSolicitud(request.getEstado_solicitud());
        solicitudexposicionExistente.setComentarios(request.getComentarios());
        solicitudexposicionExistente.setArtista(artista);
        solicitudexposicionExistente.setFechaRecepcionSolicitud(request.getFecha_recepcion_solicitud());

        SolicitudExposicion updated = solicitudexposicionRepository.save(solicitudexposicionExistente);
        return SolicitudExposicionResponse.fromEntity(updated);
    }

    public void eliminarSolicitudExposicion(Long id) {
        SolicitudExposicion solicitudexposicion = solicitudexposicionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud exposicion no encontrada con ID " + id));

        solicitudexposicionRepository.delete(solicitudexposicion);
    }

    
    
    public SolicitudExposicionResponse obtenerSolicitudesExposicionPorId(Long id) {
        SolicitudExposicion solicitudexposicion = solicitudexposicionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud de exposicion no encontrada con ID " + id));
        return SolicitudExposicionResponse.fromEntity(solicitudexposicion);
    }

    public List<SolicitudExposicionResponse> obtenerSolcitudExposicionPorArtista(Long idArtista) {
        return SolicitudExposicionResponse.fromEntities(solicitudexposicionRepository.findByArtista_IdArtista(idArtista));
    }
}
