package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.EvaluacionArtistica;
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
public class EvaluacionArtisticaResponse {
    private Long idEvaluacionArtistica;
    private ObraDeArteResponse obra;
    private EspecialistaResponse especialista;
    private LocalDate fechaEvaluacion;
    private String resultado;
    private String motivoRechazo;

    public static EvaluacionArtisticaResponse fromEntity(EvaluacionArtistica evaluacion) {
        return EvaluacionArtisticaResponse.builder()
                .idEvaluacionArtistica(evaluacion.getIdEvaluacionArtistica())
                .obra(ObraDeArteResponse.fromEntity(evaluacion.getObra()))
                .especialista(EspecialistaResponse.fromEntity(evaluacion.getEspecialista()))
                .fechaEvaluacion(evaluacion.getFechaEvaluacion())
                .resultado(evaluacion.getResultado())
                .motivoRechazo(evaluacion.getMotivoRechazo())
                .build();
    }

    public static List<EvaluacionArtisticaResponse> fromEntities(List<EvaluacionArtistica> evaluaciones) {
        return evaluaciones.stream()
                .map(EvaluacionArtisticaResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
