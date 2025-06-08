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
@Table(name="evaluaciones_artisticas")
public class EvaluacionArtistica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluacion_artistica")
    private Long idEvaluacionArtistica;
<<<<<<< HEAD

    // Relación Muchos a Uno con ObraDeArte
=======
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_solicitud_exposicion", referencedColumnName = "id_solicitud_exposicion", nullable = false)
    private SolicitudExposicion solicitudExposicion;

>>>>>>> 54190686d3925c64f2b8a30e6257b3af79f631ca
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false)
    private ObraDeArte obra;

    // Relación Muchos a Uno con Especialista
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_especialista", referencedColumnName = "id_especialista", nullable = false)
    private Especialista especialista;

    @Column(name = "fecha_evaluacion")
    private LocalDate fechaEvaluacion;

    @Column(name = "resultado")
    private String resultado;  // Aprobado, Rechazado, etc.

    @Column(name = "motivo_rechazo")
    private String motivoRechazo;  // Solo si la obra es rechazada
}
