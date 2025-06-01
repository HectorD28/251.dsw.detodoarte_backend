package dsw.detodoartebackend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="solicitudes_exposicion")
public class SolicitudExposicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long idSolicitud;

    @Column(name = "fecha_emision_solicitud")
    private LocalDate fechaEmisionSolicitud;

    @Column(name = "estado_solicitud", length = 20)
    private String estadoSolicitud;

    private String comentarios;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_artista")
    private Artista artista;

    @Column(name = "fecha_recepcion_solicitud")
    private LocalDate fechaRecepcionSolicitud;
}

