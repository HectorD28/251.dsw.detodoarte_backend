package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.CriterioEvaluacionArtistica;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriterioEvaluacionArtisticaResponse {

    private Long idCriterioEvaluacionArtistica;
    private TecnicaResponse tecnica;
    private String nombreCriterio;
    private String descripcion;

    public static CriterioEvaluacionArtisticaResponse fromEntity(CriterioEvaluacionArtistica entity) {
        return CriterioEvaluacionArtisticaResponse.builder()
                .idCriterioEvaluacionArtistica(entity.getIdCriterioEvaluacionArtistica())
                .tecnica(TecnicaResponse.fromEntity(entity.getTecnica()))
                .nombreCriterio(entity.getNombreCriterio())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
