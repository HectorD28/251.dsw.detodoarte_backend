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
@Table(name = "criterio_evaluaciones_economicas")
public class CriterioEvaluacionesEconomicas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_criterio_evaluaciones_economicas")
    private Long idCriterioEvalacuionesEconomicas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_evaluacion_economica", referencedColumnName = "id_evaluacion_economica", nullable = false)
    private EvaluacionEconomica evaluacionEconomica;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_criterio_evaluacion_economica", referencedColumnName = "id_criterio_evaluacion_economica", nullable = false)
    private CriterioEvaluacionEconomica criterioEvaluacionEconomica;

    @Column(name = "precio_criterio")
    private Integer precioCriterio;
}
