
package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.Artista;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudExposicionRequest {
    //private Long id_solicitud;
    private LocalDate fecha_emision_solicitud;
    private String estado_solicitud;
    private String comentarios;
    private Long id_artista;
    private LocalDate fecha_recepcion_solicitud;
}
