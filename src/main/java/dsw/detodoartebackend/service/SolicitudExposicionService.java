package dsw.detodoartebackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dsw.detodoartebackend.dto.SolicitudExposicionRequest;
import dsw.detodoartebackend.dto.SolicitudExposicionResponse;
import dsw.detodoartebackend.entity.Artista;
import dsw.detodoartebackend.entity.SolicitudExposicion;
import dsw.detodoartebackend.repository.ArtistaRepository;
import dsw.detodoartebackend.repository.SolicitudExposicionRepository;

@Service
public class SolicitudExposicionService {

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private SolicitudExposicionRepository solicitudExposicionRepository;

    public SolicitudExposicionResponse crearSolicitud(SolicitudExposicionRequest request) {
        // Buscar el artista por ID
        Artista artista = artistaRepository.findById(request.getArtistaId())
            .orElseThrow(() -> new RuntimeException("Artista no encontrado"));

        // Crear la solicitud con el artista y los comentarios
        SolicitudExposicion solicitud = SolicitudExposicion.builder()
            .artista(artista)
            .comentarios(request.getComentarios())
            .build();

        solicitud = solicitudExposicionRepository.save(solicitud);
        return SolicitudExposicionResponse.fromEntity(solicitud);
    }

    public List<SolicitudExposicionResponse> obtenerTodas() {
    return solicitudExposicionRepository.findAll().stream()
        .map(SolicitudExposicionResponse::fromEntity)
        .toList();
    }
}
