package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.EvaluacionEconomica;
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
public class EvaluacionEconomicaResponse {
    private Long idEvaluacionEconomica;
    private ObraDeArteResponse obra;
    private EspecialistaResponse especialista;
    private double precioVenta;
    private double porcentajeGanancia;
    private LocalDate fechaEvaluacion;
    private String resultado;
    private String motivoRechazo;

    public static EvaluacionEconomicaResponse fromEntity(EvaluacionEconomica evaluacion) {
        return EvaluacionEconomicaResponse.builder()
                .idEvaluacionEconomica(evaluacion.getIdEvaluacionEconomica())
                .obra(ObraDeArteResponse.fromEntity(evaluacion.getObra()))
                .especialista(EspecialistaResponse.fromEntity(evaluacion.getEspecialista()))
                .precioVenta(evaluacion.getPrecioVenta())
                .porcentajeGanancia(evaluacion.getPorcentajeGanancia())
                .fechaEvaluacion(evaluacion.getFechaEvaluacion())
                .resultado(evaluacion.getResultado())
                .motivoRechazo(evaluacion.getMotivoRechazo())
                .build();
    }

    public static List<EvaluacionEconomicaResponse> fromEntities(List<EvaluacionEconomica> evaluaciones) {
        return evaluaciones.stream()
                .map(EvaluacionEconomicaResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
