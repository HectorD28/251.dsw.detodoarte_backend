package dsw.detodoartebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdministradorRequest {
    private Long personaId;  // ID de la persona asociada al administrador
    private String password;
    private String tipoUsuario;  // Tipo de usuario (admin, moderador)
}
