package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.Artista;
import dsw.detodoartebackend.entity.SolicitudExposicion;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudExposicionResponse {
    private Long id_solicitud_exposicion;
    private LocalDate fecha_emision_solicitud;
    private String estadoSolicitud;
    private String comentarios;
    private Artista artista;
    private LocalDate fecha_recepcion_solicitud;
    
       public static SolicitudExposicionResponse fromEntity(SolicitudExposicion solicitudexposicion) {
        return SolicitudExposicionResponse.builder()
            .id_solicitud_exposicion(solicitudexposicion.getIdSolicitudExposicion())
            .fecha_emision_solicitud(solicitudexposicion.getFechaEmisionSolicitud())
            .estadoSolicitud(solicitudexposicion.getEstadoSolicitud())
            .comentarios(solicitudexposicion.getComentarios())
            .artista(solicitudexposicion.getArtista())
            .fecha_recepcion_solicitud(solicitudexposicion.getFechaRecepcionSolicitud())
            .build();
    }

    public static List<SolicitudExposicionResponse> fromEntities(List<SolicitudExposicion> solicitudexposiciones) {
        return solicitudexposiciones.stream()
                .map(SolicitudExposicionResponse::fromEntity)
                .collect(Collectors.toList());
    }
}