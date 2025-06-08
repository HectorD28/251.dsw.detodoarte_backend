package dsw.detodoartebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="solicitud_obra")
public class SolicitudObra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud_obra")
    private Long idSolicitudObra;

    // Relación Muchos a Uno con SolicitudExposicionPresencial
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_solicitud_exposicion_presencial", referencedColumnName = "id_solicitud_exposicion_presencial", nullable = false)
    private SolicitudExposicionPresencial solicitudExposicionPresencial;

    // Relación Muchos a Uno con ObraDeArte
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false)
    private ObraDeArte obra;  // Relación con la tabla 'obras_de_arte'
}
