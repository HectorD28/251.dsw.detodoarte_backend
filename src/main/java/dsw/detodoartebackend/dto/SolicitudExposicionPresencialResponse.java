package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.SolicitudExposicionPresencial;
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
public class SolicitudExposicionPresencialResponse {
    private Long idSolicitudExposicionPresencial;
    private ArtistaResponse artista;
    private LocalDate fechaEmisionSolicitud;
    private String estadoSolicitud;
    private String comentarios;
    private LocalDate fechaRecepcionSolicitud;

    public static SolicitudExposicionPresencialResponse fromEntity(SolicitudExposicionPresencial solicitud) {
        return SolicitudExposicionPresencialResponse.builder()
                .idSolicitudExposicionPresencial(solicitud.getIdSolicitudExposicionPresencial())
                .artista(ArtistaResponse.fromEntity(solicitud.getArtista()))
                .fechaEmisionSolicitud(solicitud.getFechaEmisionSolicitud())
                .estadoSolicitud(solicitud.getEstadoSolicitud())
                .comentarios(solicitud.getComentarios())
                .fechaRecepcionSolicitud(solicitud.getFechaRecepcionSolicitud())
                .build();
    }

    public static List<SolicitudExposicionPresencialResponse> fromEntities(List<SolicitudExposicionPresencial> solicitudes) {
        return solicitudes.stream()
                .map(SolicitudExposicionPresencialResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
