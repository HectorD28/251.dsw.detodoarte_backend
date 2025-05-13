
package dsw.detodoartebackend.dto;

import java.sql.Date;
import java.util.List;
import lombok.Data;

@Data
public class SolicitudExposicionRequest {
    private Long idSolicitud;
    private Long artistaId;
    private List<Long> obraIds;
    private Date fechaSolicitud;
    private String estadoSolicitud = "PENDIENTE";
    private String comentarios;
}
