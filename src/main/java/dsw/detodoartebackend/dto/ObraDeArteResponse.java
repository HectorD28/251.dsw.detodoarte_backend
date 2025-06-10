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
    private TecnicaResponse tecnicaId;  // Información completa de la Técnica
    private ArtistaResponse artistaId;  // Información completa del Artista
    private Double precio;
    private Integer cantidadVisualizaciones;
    private String rutaImagen;  // Ruta de la imagen de la obra
    private Integer stock;  
    private String estado_publicacion;

    public static ObraDeArteResponse fromEntity(ObraDeArte obra) {
        return ObraDeArteResponse.builder()
                .obraId(obra.getObraId())
                .titulo(obra.getTitulo())
                .fechaRealizacion(obra.getFechaRealizacion())
                .dimensiones(obra.getDimensiones())
                .tecnicaId(TecnicaResponse.fromEntity(obra.getTecnica()))
                .artistaId(ArtistaResponse.fromEntity(obra.getArtista()))
                .precio(obra.getPrecio())
                .cantidadVisualizaciones(obra.getCantidadVisualizaciones())
                .rutaImagen(obra.getRutaImagen())
                .stock(obra.getStock())
                .build();
    }

    public static List<ObraDeArteResponse> fromEntities(List<ObraDeArte> obras) {
        return obras.stream()
                .map(ObraDeArteResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
