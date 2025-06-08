package dsw.detodoartebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionArtisticaRequest {
    private Long idObra;
    private Long idEspecialista;
    private LocalDate fechaEvaluacion;
    private String resultado;
    private String motivoRechazo;
}
