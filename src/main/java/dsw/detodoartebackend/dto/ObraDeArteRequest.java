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
<<<<<<< HEAD
=======
    private String ruta_imagen;
    
>>>>>>> 54190686d3925c64f2b8a30e6257b3af79f631ca
}
