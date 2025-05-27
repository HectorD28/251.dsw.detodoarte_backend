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
    private Long persona_id;
    private String dni;
    private String nombreCompleto;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
    private char sexo;
    private String telefono;
    private String correoElectronico;
    private String contrasena;
    private String rol; // mapeado a rol en la base de datos
    private boolean estado; // mapeado a estado en la base de datos
    private String username;
    
    public static PersonaResponse fromEntity(Personas persona) {
        return PersonaResponse.builder()
            .persona_id(persona.getPersona_id())
            .dni(persona.getDni())
            .nombreCompleto(persona.getNombreCompleto()== null ? "" : persona.getNombreCompleto())
            .apellidoPaterno(persona.getApellidoPaterno() == null ? "" : persona.getApellidoPaterno())
            .apellidoMaterno(persona.getApellidoMaterno() == null ? "" : persona.getApellidoMaterno())
            .direccion(persona.getDireccion() == null ? "" : persona.getDireccion())
            .sexo(persona.getSexo())
            .telefono(persona.getTelefono())
            .correoElectronico(persona.getCorreoElectronico()== null ? "" : persona.getCorreoElectronico())
            .contrasena(persona.getContrasena()== null ? "" : persona.getContrasena())
            .rol(persona.getRol() == null ? "" : persona.getRol())
            .estado(persona.isEstado())
            .username(persona.getUsername()== null ? "" : persona.getUsername())
            .build();
    }

    public static List<PersonaResponse> fromEntities(List<Personas> personas) {
        return personas.stream()
                .map(PersonaResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public PersonaResponse(Personas persona) {
        this.persona_id = persona.getPersona_id();
        this.dni = persona.getDni();
        this.nombreCompleto = persona.getNombreCompleto();
        this.apellidoPaterno = persona.getApellidoPaterno();
        this.apellidoMaterno = persona.getApellidoMaterno();
        this.direccion = persona.getDireccion();
        this.sexo = persona.getSexo();
        this.telefono = persona.getTelefono();
        this.correoElectronico = persona.getCorreoElectronico();
        this.contrasena = persona.getContrasena();
        this.rol = persona.getRol();
        this.estado = persona.isEstado();
        this.username = persona.getUsername();
    }
            
    
}
