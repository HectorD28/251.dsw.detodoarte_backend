package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.EspacioGaleria;
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
public class EspacioGaleriaResponse {
    private Long idEspacio;
    private String nombreEspacio;
    private String descripcion;

    public static EspacioGaleriaResponse fromEntity(EspacioGaleria espacio) {
        return EspacioGaleriaResponse.builder()
                .idEspacio(espacio.getIdEspacio())
                .nombreEspacio(espacio.getNombreEspacio())
                .descripcion(espacio.getDescripcion())
                .build();
    }

    public static List<EspacioGaleriaResponse> fromEntities(List<EspacioGaleria> espacios) {
        return espacios.stream()
                .map(EspacioGaleriaResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
