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
    @Column(name = "id_solicitud_exposicion")
<<<<<<< HEAD
    private Long idSolicitudExposicion;
=======
    private Long idSolicitud;
>>>>>>> 54190686d3925c64f2b8a30e6257b3af79f631ca

    @Column(name = "fecha_emision_solicitud")
    private LocalDate fechaEmisionSolicitud;

    @Column(name = "estado_solicitud", length = 20)
    private String estadoSolicitud;
    
    @Column(name = "comentarios", length = 20)
    private String comentarios;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_artista", referencedColumnName = "id_artista", nullable = false)
    private Artista artista;

    @Column(name = "fecha_recepcion_solicitud")
    private LocalDate fechaRecepcionSolicitud;
}

