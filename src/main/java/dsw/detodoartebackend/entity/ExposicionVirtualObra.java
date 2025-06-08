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
@Table(name="exposiciones_virtuales_obras")
public class ExposicionVirtualObra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exposicion_virtual_obra")
    private Long idExposicionVirtualObra;

    // Relación Muchos a Uno con ExposicionVirtual
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_exposicion_virtual", referencedColumnName = "id_exposicion_virtual", nullable = false)
    private ExposicionVirtual exposicionVirtual;

    // Relación Muchos a Uno con ObraDeArte
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false)
    private ObraDeArte obra;  // Relación con la tabla 'obras_de_arte'
}
