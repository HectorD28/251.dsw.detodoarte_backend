/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.Especialista;
import dsw.detodoartebackend.entity.Personas;
import dsw.detodoartebackend.entity.Tecnica;
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
    private Long id_especialista;
    private Personas persona; 
    private Tecnica tecnica; 


    public static EspecialistaResponse fromEntity(Especialista especialista) {
        return EspecialistaResponse.builder()
            .id_especialista(especialista.getId_especialista())
            .persona(especialista.getPersona())
            .tecnica(especialista.getTecnica())
            .build();    
    }

    public static List<EspecialistaResponse> fromEntities(List<Especialista> especialista) {
        return especialista.stream()
                .map(EspecialistaResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
