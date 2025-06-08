package dsw.detodoartebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExposicionVirtualObraRequest {
    private Long idExposicionVirtual;  // ID de la exposición virtual
    private Long idObra;  // ID de la obra a asociar
}
