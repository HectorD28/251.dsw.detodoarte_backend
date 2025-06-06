package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.Artista;
import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.entity.Tecnica;
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
    private Long ObraId;
    private String titulo;
    private LocalDate fechaRealizacion;
    private String dimensiones;
    private Tecnica tecnicaId;  // ID de la técnica
    private Artista artistaId;  // ID del artista
    private Double precio;
    private Integer cantidadVisualizacines;
    private String rutaImagen;
    private Integer stock;
    
    public static ObraDeArteResponse fromEntity(ObraDeArte obradearte) {
        return ObraDeArteResponse.builder()
            .ObraId(obradearte.getObraId())
            .titulo(obradearte.getTitulo())
            .fechaRealizacion(obradearte.getFechaRealizacion())
            .dimensiones(obradearte.getDimensiones())
            .tecnicaId(obradearte.getTecnica()) // Acceso a la técnica asociada
            .artistaId(obradearte.getArtista()) // Acceso al artista asociado
            .precio(obradearte.getPrecio())
            .cantidadVisualizacines(obradearte.getCantidadVisualizaciones())
            .rutaImagen(obradearte.getRutaImagen())
            .stock(obradearte.getStock())
            .build();
    }

    public static List<ObraDeArteResponse> fromEntities(List<ObraDeArte> obradeartes) {
        return obradeartes.stream()
                .map(ObraDeArteResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
