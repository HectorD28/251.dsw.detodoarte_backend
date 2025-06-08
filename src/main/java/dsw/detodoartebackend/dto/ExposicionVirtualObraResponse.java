package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.ExposicionVirtualObra;
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
public class ExposicionVirtualObraResponse {
    private Long idExposicionVirtualObra;
    private ExposicionVirtualResponse exposicionVirtual;
    private ObraDeArteResponse obra;

    public static ExposicionVirtualObraResponse fromEntity(ExposicionVirtualObra exposicionVirtualObra) {
        return ExposicionVirtualObraResponse.builder()
                .idExposicionVirtualObra(exposicionVirtualObra.getIdExposicionVirtualObra())
                .exposicionVirtual(ExposicionVirtualResponse.fromEntity(exposicionVirtualObra.getExposicionVirtual()))
                .obra(ObraDeArteResponse.fromEntity(exposicionVirtualObra.getObra()))
                .build();
    }

    public static List<ExposicionVirtualObraResponse> fromEntities(List<ExposicionVirtualObra> exposicionesObras) {
        return exposicionesObras.stream()
                .map(ExposicionVirtualObraResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
