package dsw.detodoartebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriterioEvaluacionArtisticaRequest {

    private Long id_tecnica;
    private String nombre_criterio;
    private String descripcion;
}
