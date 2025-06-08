package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.Exposicion;
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
public class ExposicionResponse {
    private Long id_exposicion;
    private String nombre;
    private String descripcion;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin; 
    private String tipo_exposicion;

    public static ExposicionResponse fromEntity(Exposicion exposicion) {
        return ExposicionResponse.builder()
            .id_exposicion(exposicion.getId_exposicion())
            .nombre(exposicion.getNombre())
            .descripcion(exposicion.getDescripcion())
            .fecha_inicio(exposicion.getFecha_inicio())
            .fecha_fin(exposicion.getFecha_fin())
            .tipo_exposicion(exposicion.getTipo_exposicion())
            .build();
    }

    public static List<ExposicionResponse> fromEntities(List<Exposicion> exposiciones) {
        return exposiciones.stream()
                .map(ExposicionResponse::fromEntity)
                .collect(Collectors.toList());
    }
}