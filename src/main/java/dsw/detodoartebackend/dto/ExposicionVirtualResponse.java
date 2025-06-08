package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.ExposicionVirtual;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExposicionVirtualResponse {
    private Long idExposicionVirtual;
    private LocalDate fechaPublicacion;
    private String estadoPublicacion;
    private String urlPublicacion;
    private String comentarios;

    public static ExposicionVirtualResponse fromEntity(ExposicionVirtual exposicion) {
        return ExposicionVirtualResponse.builder()
                .idExposicionVirtual(exposicion.getIdExposicionVirtual())
                .fechaPublicacion(exposicion.getFechaPublicacion())
                .estadoPublicacion(exposicion.getEstadoPublicacion())
                .urlPublicacion(exposicion.getUrlPublicacion())
                .comentarios(exposicion.getComentarios())
                .build();
    }

    public static List<ExposicionVirtualResponse> fromEntities(List<ExposicionVirtual> exposiciones) {
        return exposiciones.stream()
                .map(ExposicionVirtualResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
