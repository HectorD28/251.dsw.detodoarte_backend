package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.Administrador;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdministradorResponse {
    private Long idAdministrador;
    private PersonaResponse persona;
    private String password;
    private String tipoUsuario;

    public static AdministradorResponse fromEntity(Administrador administrador) {
        return AdministradorResponse.builder()
                .idAdministrador(administrador.getIdAdministrador())
                .persona(PersonaResponse.fromEntity(administrador.getPersona()))
                .password(administrador.getPassword())
                .tipoUsuario(administrador.getTipoUsuario())
                .build();
    }

    public static List<AdministradorResponse> fromEntities(List<Administrador> administradores) {
        return administradores.stream()
                .map(AdministradorResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
