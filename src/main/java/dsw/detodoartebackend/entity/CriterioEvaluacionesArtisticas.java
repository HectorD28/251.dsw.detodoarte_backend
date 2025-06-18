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
@Table(name = "criterio_evaluaciones_artisticas")
public class CriterioEvaluacionesArtisticas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_criterios_evaluaciones_artisticas")
    private Long idCriterioEvaluacionesArtisticas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_evaluacion_artistica", referencedColumnName = "id_evaluacion_artistica", nullable = false)
    private EvaluacionArtistica evaluacionArtistica;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_criterio_evaluacion_artistica", referencedColumnName = "id_criterio_evaluacion_artistica", nullable = false)
    private CriterioEvaluacionArtistica criterioEvaluacionArtisitica;

    @Column(name = "puntaje_criterio")
    private Integer puntajeCriterio;
}
