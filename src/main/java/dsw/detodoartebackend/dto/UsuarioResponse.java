package dsw.detodoartebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {
    private String nombreCompleto;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String dni;
    private String correoElectronico;
    private String rol; 
    private String telefono;
}
