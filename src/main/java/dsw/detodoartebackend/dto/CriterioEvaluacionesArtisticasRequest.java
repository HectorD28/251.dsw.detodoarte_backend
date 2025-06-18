package dsw.detodoartebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriterioEvaluacionesArtisticasRequest {
    private Long id_evaluacion_artistica;
    private Long id_criterio_evaluacion_artistica;
    private Integer puntaje_criterio;
}
