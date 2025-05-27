/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.Tecnica;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TecnicaResponse {
    private Long id_tecnica;
    private String nombre_tecnica;

    // Conversor de entidad a DTO Response
    public static TecnicaResponse fromEntity(Tecnica tecnica) {
        return TecnicaResponse.builder()
            .id_tecnica(tecnica.getIdTecnica())
            .nombre_tecnica(tecnica.getNombreTecnica())
            .build();
    }
}
