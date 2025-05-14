
package dsw.detodoartebackend.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SolicitudExposicionRequest {
    private Long artistaId;
    private String comentarios;
    //private List<Long> obraIds;
}
