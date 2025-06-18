package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.CriterioEvaluacionesEconomicas;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriterioEvaluacionesEconomicasResponse {
    private Long idCriterioEvalacuionesEconomicas;
    private EvaluacionEconomicaResponse evaluacionEconomica;
    private CriterioEvaluacionEconomicaResponse criterioEvaluacionEconomica;
    private Integer precioCriterio;

    public static CriterioEvaluacionesEconomicasResponse fromEntity(CriterioEvaluacionesEconomicas entity) {
        return CriterioEvaluacionesEconomicasResponse.builder()
                .idCriterioEvalacuionesEconomicas(entity.getIdCriterioEvalacuionesEconomicas())
                .evaluacionEconomica(EvaluacionEconomicaResponse.fromEntity(entity.getEvaluacionEconomica()))
                .criterioEvaluacionEconomica(CriterioEvaluacionEconomicaResponse.fromEntity(entity.getCriterioEvaluacionEconomica()))
                .precioCriterio(entity.getPrecioCriterio())
                .build();
    }
}