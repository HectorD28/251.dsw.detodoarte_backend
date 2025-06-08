package dsw.detodoartebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="solicitudes_exposicion_presencial")
public class SolicitudExposicionPresencial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud_exposicion_presencial")
    private Long idSolicitudExposicionPresencial;

    // Relación Muchos a Uno con el Artista
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_artista", referencedColumnName = "id_artista", nullable = false)
    private Artista artista;  // Relación con la tabla 'artistas'

    @Column(name = "fecha_emision_solicitud")
    private LocalDate fechaEmisionSolicitud;

    @Column(name = "estado_solicitud")
    private String estadoSolicitud;  // Pendiente, Aprobada, Rechazada, etc.

    @Column(name = "comentarios")
    private String comentarios;  // Comentarios adicionales sobre la solicitud

    @Column(name = "fecha_recepcion_solicitud")
    private LocalDate fechaRecepcionSolicitud;  // Fecha de recepción de la solicitud
}
