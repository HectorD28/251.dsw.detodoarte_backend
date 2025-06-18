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
@Table(name = "criterio_evaluacion_economica")
public class CriterioEvaluacionEconomica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_criterio_evaluacion_economica")
    private Long idCriterioEvaluacionEconomica;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tecnica", referencedColumnName = "id_tecnica", nullable = false)
    private Tecnica tecnica;

    @Column(name = "nombre_criterio")
    private String nombreCriterio;

    @Column(name = "descripcion")
    private String descripcion;
}