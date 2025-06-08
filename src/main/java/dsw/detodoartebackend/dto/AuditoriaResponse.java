package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.Auditoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaResponse {
    private Long idAuditoria;
    private String tablaModificada;
    private Long idRegistroModificado;
    private String campoModificado;
    private String valorAnterior;
    private String valorNuevo;
    private LocalDateTime fechaModificacion;
    private PersonaResponse persona;

    public static AuditoriaResponse fromEntity(Auditoria auditoria) {
        return AuditoriaResponse.builder()
                .idAuditoria(auditoria.getIdAuditoria())
                .tablaModificada(auditoria.getTablaModificada())
                .idRegistroModificado(auditoria.getIdRegistroModificado())
                .campoModificado(auditoria.getCampoModificado())
                .valorAnterior(auditoria.getValorAnterior())
                .valorNuevo(auditoria.getValorNuevo())
                .fechaModificacion(auditoria.getFechaModificacion())
                .persona(PersonaResponse.fromEntity(auditoria.getPersona()))  // Transformar la persona
                .build();
    }

    public static List<AuditoriaResponse> fromEntities(List<Auditoria> auditorias) {
        return auditorias.stream()
                .map(AuditoriaResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
