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
public class ArtistaResponse {
    private Long personaId;
    private String dni;
    private String nombreCompleto;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
    private char sexo;
    private String telefono;
    private String correoElectronico;

    public static ArtistaResponse fromEntity(Artista artista) {
        return ArtistaResponse.builder()
            .idArtista(artista.getId())
            .personaId(artista.getPersona().getPersonaId())
            .build();
    }

    public static List<ArtistaResponse> fromEntities(List<Artista> artistas) {
        return artistas.stream()
                .map(ArtistaResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
