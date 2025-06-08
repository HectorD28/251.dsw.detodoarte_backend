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
public class ExposicionVirtualRequest {
    private LocalDate fechaPublicacion;
    private String estadoPublicacion;  // Pendiente, Publicada, etc.
    private String urlPublicacion;
    private String comentarios;
}
