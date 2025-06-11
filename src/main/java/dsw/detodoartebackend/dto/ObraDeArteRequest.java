package dsw.detodoartebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObraDeArteRequest {
    private String titulo;
    private LocalDate fecha_realizacion;
    private String dimensiones;
    private Long id_tecnica;
    private Long id_artista;
    private Double precio;
    private String ruta_imagen;
    private Integer cantidad_visualizaciones;
    private Integer stock;  

}
