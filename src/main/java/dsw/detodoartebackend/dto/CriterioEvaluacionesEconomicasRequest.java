package dsw.detodoartebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriterioEvaluacionesEconomicasRequest {
    private Long id_evaluacion_economica;
    private Long id_criterio_evaluacion_economica;
    private Integer precio_criterio;
}