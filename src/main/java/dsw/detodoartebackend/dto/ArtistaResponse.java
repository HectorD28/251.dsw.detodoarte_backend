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
    private Long idArtista;
    private Personas persona; // Incluir el objeto completo de Persona

    public static ArtistaResponse fromEntity(Artista artista) {
        return ArtistaResponse.builder()
                .idArtista(artista.getIdArtista())
                .persona(artista.getPersona()) // Incluir la persona completa
                .build();
    }

    public static List<ArtistaResponse> fromEntities(List<Artista> artistas) {
        return artistas.stream()
                .map(ArtistaResponse::fromEntity)
                .collect(Collectors.toList());
    }
}