package dsw.detodoartebackend.dto;

import java.sql.Date;
import java.util.List;

import dsw.detodoartebackend.entity.SolicitudExposicion;
import lombok.Data;

@Data
public class SolicitudExposicionResponse {
    private Long idSolicitud;
    // private Long artistaId;
    private String nombre;
    private String apellido;
    private List<Long> obraIds;
    private Date fechaSolicitud;
    private String estadoSolicitud;
    private String comentarios;

    public static SolicitudExposicionResponse fromEntity(SolicitudExposicion solicitud) {
        SolicitudExposicionResponse response = new SolicitudExposicionResponse();
        response.setIdSolicitud(solicitud.getIdSolicitud());
        // response.setArtistaId(solicitud.getArtista().getId_artista());
        // Si tu entidad tiene la lista de obras, mapea sus IDs:
        /*if (solicitud.getObras() != null) {
            response.setObraIds(
                solicitud.getObras().stream()
                    .map(obra -> obra.getObra().getId())
                    .toList()
            );
        }*/
        response.setNombre(solicitud.getArtista().getPersona().getNombreCompleto());
        response.setApellido(solicitud.getArtista().getPersona().getApellidoPaterno() + " " +
                solicitud.getArtista().getPersona().getApellidoMaterno());
        if (solicitud.getFechaSolicitud() != null) {
            response.setFechaSolicitud(Date.valueOf(solicitud.getFechaSolicitud().toLocalDate()));
        }
        response.setEstadoSolicitud(solicitud.getEstadoSolicitud());
        response.setComentarios(solicitud.getComentarios());
        // Mapea los IDs de las obras asociadas
        if (solicitud.getObras() != null) {
            response.setObraIds(
                solicitud.getObras().stream()
                    .filter(so -> {
                        if (so == null || so.getObra() == null) {
                            System.out.println("SolicitudObra o su obra es null");
                            return false;
                        }
                        return true;
                    })
                    .map(so -> so.getObra().getObraId())
                    .toList()
            );
        }
        return response;
    }
}
