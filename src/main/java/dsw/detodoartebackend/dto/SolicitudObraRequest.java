package dsw.detodoartebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudObraRequest {
    private Long idSolicitudExposicionPresencial;  // ID de la solicitud de exposición
    private Long idObra;  // ID de la obra a exponer
}
