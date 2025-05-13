/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.Personas;
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
public class PersonaResponse {
    private Long personaId;
    private String dni;
    private String nombreCompleto;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
    private char sexo;
    private String telefono;
    private String correoElectronico;

    public static PersonaResponse fromEntity(Personas persona) {
        return PersonaResponse.builder()
            .personaId(persona.getPersonaId())
            .dni(persona.getDni())
            .nombreCompleto(persona.getNombreCompleto())
            .apellidoPaterno(persona.getApellidoPaterno() == null ? "" : persona.getApellidoPaterno())
            .apellidoMaterno(persona.getApellidoMaterno() == null ? "" : persona.getApellidoMaterno())
            .direccion(persona.getDireccion() == null ? "" : persona.getDireccion())
            .sexo(persona.getSexo())
            .telefono(persona.getTelefono())
            .correoElectronico(persona.getCorreoElectronico())
            .build();
    }

    public static List<PersonaResponse> fromEntities(List<Personas> personas) {
        return personas.stream()
                .map(PersonaResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
