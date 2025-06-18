package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.CriterioEvaluacionesArtisticas;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriterioEvaluacionesArtisticasResponse {
    private Long idCriterioEvaluacionesArtisticas;
    private CriterioEvaluacionArtisticaResponse criterioEvaluacionArtisitica;
    private EvaluacionArtisticaResponse evaluacionArtistica;
    private Integer puntajeCriterio;

    public static CriterioEvaluacionesArtisticasResponse fromEntity(CriterioEvaluacionesArtisticas entity) {
        return CriterioEvaluacionesArtisticasResponse.builder()
                .idCriterioEvaluacionesArtisticas(entity.getIdCriterioEvaluacionesArtisticas())
                .criterioEvaluacionArtisitica(CriterioEvaluacionArtisticaResponse.fromEntity(entity.getCriterioEvaluacionArtisitica()))
                .evaluacionArtistica(EvaluacionArtisticaResponse.fromEntity(entity.getEvaluacionArtistica()))
                .puntajeCriterio(entity.getPuntajeCriterio())
                .build();
    }
}
