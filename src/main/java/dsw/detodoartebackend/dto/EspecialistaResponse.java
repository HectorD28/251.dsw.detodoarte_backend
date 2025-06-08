package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.Especialista;
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
public class EspecialistaResponse {
    private Long idEspecialista;
    private PersonaResponse persona;  // Información completa de la Persona
    private TecnicaResponse tecnica;  // Información completa de la Técnica

    public static EspecialistaResponse fromEntity(Especialista especialista) {
        return EspecialistaResponse.builder()
                .idEspecialista(especialista.getIdEspecialista())
                .persona(PersonaResponse.fromEntity(especialista.getPersona())) // Convertir la Persona
                .tecnica(TecnicaResponse.fromEntity(especialista.getTecnica())) // Convertir la Técnica
                .build();
    }

    public static List<EspecialistaResponse> fromEntities(List<Especialista> especialistas) {
        return especialistas.stream()
                .map(EspecialistaResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
