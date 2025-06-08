package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.ExposicionPresencial;
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
public class ExposicionPresencialResponse {
    private Long idExposicionPresencial;
    private SolicitudExposicionPresencialResponse solicitudExposicionPresencial;
    private EspacioGaleriaResponse espacioGaleria;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String tipoExposicion;

    public static ExposicionPresencialResponse fromEntity(ExposicionPresencial exposicion) {
        return ExposicionPresencialResponse.builder()
                .idExposicionPresencial(exposicion.getIdExposicionPresencial())
                .solicitudExposicionPresencial(SolicitudExposicionPresencialResponse.fromEntity(exposicion.getSolicitudExposicionPresencial()))
                .espacioGaleria(EspacioGaleriaResponse.fromEntity(exposicion.getEspacioGaleria()))
                .fechaInicio(exposicion.getFechaInicio())
                .fechaFin(exposicion.getFechaFin())
                .tipoExposicion(exposicion.getTipoExposicion())
                .build();
    }

    public static List<ExposicionPresencialResponse> fromEntities(List<ExposicionPresencial> exposiciones) {
        return exposiciones.stream()
                .map(ExposicionPresencialResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
