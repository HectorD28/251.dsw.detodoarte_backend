/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.Artista;
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
public class ArtistaResponse {
    private long id_artista;
    private Personas persona;


    public static ArtistaResponse fromEntity(Artista artista) {
        return ArtistaResponse.builder()
            .id_artista(artista.getId_artista())
            .persona(artista.getPersona())
            .build();    
    }

    public static List<ArtistaResponse> fromEntities(List<Artista> artistas) {
        return artistas.stream()
                .map(ArtistaResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
