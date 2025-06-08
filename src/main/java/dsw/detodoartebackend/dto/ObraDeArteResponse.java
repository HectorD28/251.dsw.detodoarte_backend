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
<<<<<<< HEAD

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
=======
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
>>>>>>> 54190686d3925c64f2b8a30e6257b3af79f631ca
    }

    public static List<ObraDeArteResponse> fromEntities(List<ObraDeArte> obras) {
        return obras.stream()
                .map(ObraDeArteResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
