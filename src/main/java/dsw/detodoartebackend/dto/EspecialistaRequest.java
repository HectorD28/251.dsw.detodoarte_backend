package dsw.detodoartebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EspecialistaRequest {
    private Long personaId;  // ID de la persona asociada al especialista
    private Long tecnicaId;  // ID de la técnica asociada al especialista
}
