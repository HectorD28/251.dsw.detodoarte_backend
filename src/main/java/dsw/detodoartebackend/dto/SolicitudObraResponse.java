package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.SolicitudObra;
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
public class SolicitudObraResponse {
    private Long idSolicitudObra;
    private SolicitudExposicionPresencialResponse solicitudExposicionPresencial;
    private ObraDeArteResponse obra;

    public static SolicitudObraResponse fromEntity(SolicitudObra solicitud) {
        return SolicitudObraResponse.builder()
                .idSolicitudObra(solicitud.getIdSolicitudObra())
                .solicitudExposicionPresencial(SolicitudExposicionPresencialResponse.fromEntity(solicitud.getSolicitudExposicionPresencial()))
                .obra(ObraDeArteResponse.fromEntity(solicitud.getObra()))
                .build();
    }

    public static List<SolicitudObraResponse> fromEntities(List<SolicitudObra> solicitudes) {
        return solicitudes.stream()
                .map(SolicitudObraResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
