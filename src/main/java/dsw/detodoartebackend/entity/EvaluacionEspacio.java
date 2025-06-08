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
@Table(name="evaluacion_espacio")
public class EvaluacionEspacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluacion_espacio")
    private Long idEvaluacionEspacio;

    // Relación Muchos a Uno con ObraDeArte
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false)
    private ObraDeArte obra;

    // Relación Muchos a Uno con EspacioGaleria
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_espacio", referencedColumnName = "id_espacio", nullable = false)
    private EspacioGaleria espacioGaleria;

    // Relación Muchos a Uno con Administrador
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_administrador", referencedColumnName = "id_administrador", nullable = false)
    private Administrador administrador;

    @Column(name = "resultado")
    private String resultado;  // Aprobado, Rechazado, etc.

    @Column(name = "comentarios")
    private String comentarios;  // Comentarios sobre la evaluación
}
