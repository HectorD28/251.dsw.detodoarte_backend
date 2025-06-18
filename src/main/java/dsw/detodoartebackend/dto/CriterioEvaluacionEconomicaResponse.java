package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.CriterioEvaluacionEconomica;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriterioEvaluacionEconomicaResponse {
    private Long idCriterioEvaluacionEconomica;
    private TecnicaResponse tecnica;
    private String nombreCriterio;
    private String descripcion;

    public static CriterioEvaluacionEconomicaResponse fromEntity(CriterioEvaluacionEconomica entity) {
        return CriterioEvaluacionEconomicaResponse.builder()
                .idCriterioEvaluacionEconomica(entity.getIdCriterioEvaluacionEconomica())
                .tecnica(TecnicaResponse.fromEntity(entity.getTecnica()))
                .nombreCriterio(entity.getNombreCriterio())
                .descripcion(entity.getDescripcion())
                .build();
    }
}