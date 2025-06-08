package dsw.detodoartebackend.dto;

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
public class TecnicaResponse {
    private Long idTecnica;
    private String nombreTecnica;

    public static TecnicaResponse fromEntity(Tecnica tecnica) {
        return TecnicaResponse.builder()
                .idTecnica(tecnica.getIdTecnica())
                .nombreTecnica(tecnica.getNombreTecnica())
                .build();
    }
        // Método que convierte una lista de entidades Tecnica a una lista de TecnicaResponse
    public static List<TecnicaResponse> fromEntities(List<Tecnica> tecnicas) {
        return tecnicas.stream()
                .map(TecnicaResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
