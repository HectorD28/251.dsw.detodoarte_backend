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
@Table(name="exposiciones_presenciales")
public class ExposicionPresencial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exposicion_presencial")
    private Long idExposicionPresencial;

    // Relación Muchos a Uno con SolicitudExposicionPresencial
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_solicitud_exposicion_presencial", referencedColumnName = "id_solicitud_exposicion_presencial", nullable = false)
    private SolicitudExposicionPresencial solicitudExposicionPresencial;

    // Relación Muchos a Uno con EspaciosGaleria
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_espacio", referencedColumnName = "id_espacio", nullable = false)
    private EspacioGaleria espacioGaleria;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "tipo_exposicion")
    private String tipoExposicion;  // Individual, Colectiva, etc.
}
