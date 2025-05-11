/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaRequest {
    private Long personaId;               // Mapeado como persona_id en la base de datos
    private String dni;       // mapeado a apellido_paterno en la base de datos
    private String nombre_completo;       // mapeado a apellido_materno en la base de datos
    private String apellido_paterno;               // mapeado a nombre_completo
    private String apellido_materno;         // mapeado a fecha_nacimiento
    private String direccion_residencia;            // mapeado a dni
    private char sexo;             // mapeado a direccion_residencia
    private String telefono;                  // mapeado a sexo
    private String correo_electronico;              // mapeado a telefono
    
}