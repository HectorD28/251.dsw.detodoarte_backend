package dsw.detodoartebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionEspacioRequest {
    private Long idObra;
    private Long idEspacio;
    private Long idAdministrador;
    private String resultado;  // Aprobado, Rechazado, etc.
    private String comentarios;
}
