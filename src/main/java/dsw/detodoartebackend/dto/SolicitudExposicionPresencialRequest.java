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
public class SolicitudExposicionPresencialRequest {
    private Long idArtista;  // ID del artista asociado a la solicitud
    private LocalDate fechaEmisionSolicitud;
    private String estadoSolicitud;  // Estado de la solicitud (Pendiente, Aprobada, Rechazada, etc.)
    private String comentarios;
    private LocalDate fechaRecepcionSolicitud;
}
