package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.EvaluacionEspacio;
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
public class EvaluacionEspacioResponse {
    private Long idEvaluacionEspacio;
    private ObraDeArteResponse obra;
    private EspacioGaleriaResponse espacioGaleria;
    private AdministradorResponse administrador;
    private String resultado;
    private String comentarios;

    public static EvaluacionEspacioResponse fromEntity(EvaluacionEspacio evaluacion) {
        return EvaluacionEspacioResponse.builder()
                .idEvaluacionEspacio(evaluacion.getIdEvaluacionEspacio())
                .obra(ObraDeArteResponse.fromEntity(evaluacion.getObra()))
                .espacioGaleria(EspacioGaleriaResponse.fromEntity(evaluacion.getEspacioGaleria()))
                .administrador(AdministradorResponse.fromEntity(evaluacion.getAdministrador()))
                .resultado(evaluacion.getResultado())
                .comentarios(evaluacion.getComentarios())
                .build();
    }

    public static List<EvaluacionEspacioResponse> fromEntities(List<EvaluacionEspacio> evaluaciones) {
        return evaluaciones.stream()
                .map(EvaluacionEspacioResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
