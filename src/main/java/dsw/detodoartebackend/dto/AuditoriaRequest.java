package dsw.detodoartebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaRequest {
    private String tablaModificada;
    private Long idRegistroModificado;
    private String campoModificado;
    private String valorAnterior;
    private String valorNuevo;
    private Long personaId;  // El ID de la persona que hizo la modificación
}
