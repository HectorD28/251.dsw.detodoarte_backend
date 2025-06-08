package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.ObraDeArte;
import java.time.LocalDate;
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
public class ObraDeArteResponse {
    private Long obraId;
    private String titulo;
    private LocalDate fechaRealizacion;
    private String dimensiones;
    private TecnicaResponse tecnica;  // Información completa de la Técnica
    private ArtistaResponse artista;  // Información completa del Artista
    private Double precio;

    public static ObraDeArteResponse fromEntity(ObraDeArte obra) {
        return ObraDeArteResponse.builder()
                .obraId(obra.getIdObra())
                .titulo(obra.getTitulo())
                .fechaRealizacion(obra.getFechaRealizacion())
                .dimensiones(obra.getDimensiones())
                .tecnica(TecnicaResponse.fromEntity(obra.getTecnica()))
                .artista(ArtistaResponse.fromEntity(obra.getArtista()))
                .precio(obra.getPrecio())
                .build();
    }

    public static List<ObraDeArteResponse> fromEntities(List<ObraDeArte> obras) {
        return obras.stream()
                .map(ObraDeArteResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
