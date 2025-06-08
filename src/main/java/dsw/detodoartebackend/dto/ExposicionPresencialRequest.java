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
public class ExposicionPresencialRequest {
    private Long idSolicitudExposicionPresencial;  // ID de la solicitud de exposición
    private Long idEspacioGaleria;  // ID del espacio de galería
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String tipoExposicion;  // Tipo de exposición (Individual, Colectiva, etc.)
}
