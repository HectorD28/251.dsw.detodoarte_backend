package dsw.detodoartebackend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dsw.detodoartebackend.dto.SolicitudExposicionRequest;
import dsw.detodoartebackend.dto.SolicitudExposicionResponse;
import dsw.detodoartebackend.entity.Artista;
import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.entity.SolicitudExposicion;
import dsw.detodoartebackend.entity.SolicitudObra;
import dsw.detodoartebackend.repository.ArtistaRepository;
import dsw.detodoartebackend.repository.ObraDeArteRepository;
import dsw.detodoartebackend.repository.SolicitudExposicionRepository;
import dsw.detodoartebackend.repository.SolicitudObraRepository;

@Service
public class SolicitudExposicionService {

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private SolicitudExposicionRepository solicitudExposicionRepository;

    @Autowired
    private ObraDeArteRepository obraDeArteRepository;

    @Autowired
    private SolicitudObraRepository solicitudObraRepository;

    public SolicitudExposicionResponse crearSolicitud(SolicitudExposicionRequest request) {
        try {
            System.out.println("Buscando artista...");
            Artista artista = artistaRepository.findById(request.getArtistaId())
                .orElseThrow(() -> new RuntimeException("Artista no encontrado"));
            System.out.println("Artista encontrado: " + artista.getIdArtista());

            System.out.println("Buscando obras...");
            List<ObraDeArte> obras = obraDeArteRepository.findAllById(request.getObraIds());
            System.out.println("Obras encontradas: " + obras.size());

            SolicitudExposicion solicitud = SolicitudExposicion.builder()
                .artista(artista)
                .comentarios(request.getComentarios())
                .estadoSolicitud("PENDIENTE")
                .fechaSolicitud(LocalDateTime.now())
                .build();

            SolicitudExposicion solicitudGuardada = solicitudExposicionRepository.save(solicitud);
            System.out.println("Solicitud guardada: " + solicitudGuardada.getIdSolicitud());

            List<SolicitudObra> solicitudObras = obras.stream()
                .map(obra -> SolicitudObra.builder()
                    .solicitud(solicitudGuardada)
                    .obra(obra)
                    .estadoObra("PENDIENTE")
                    .build())
                .toList();

            solicitudObraRepository.saveAll(solicitudObras);
            System.out.println("SolicitudObra guardadas: " + solicitudObras.size());

            solicitudGuardada.setObras(solicitudObras);
            solicitudExposicionRepository.save(solicitudGuardada);

            SolicitudExposicion solicitudFinal = solicitudExposicionRepository
                .findById(solicitudGuardada.getIdSolicitud())
                .orElseThrow(() -> new RuntimeException("No se pudo recuperar la solicitud creada"));

            System.out.println("Obras asociadas a la solicitud final: " + 
                (solicitudFinal.getObras() != null ? solicitudFinal.getObras().size() : "null"));

            return SolicitudExposicionResponse.fromEntity(solicitudFinal);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear la solicitud: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error inesperado al crear la solicitud.");
        }
    }

    public List<SolicitudExposicionResponse> obtenerTodas() {
    return solicitudExposicionRepository.findAll().stream()
        .map(SolicitudExposicionResponse::fromEntity)
        .toList();
    }
}
