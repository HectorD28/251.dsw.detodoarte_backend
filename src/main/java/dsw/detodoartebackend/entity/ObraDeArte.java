
package dsw.detodoartebackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="obras_de_arte")
public class ObraDeArte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obra")
    private Long ObraId;
    
    @Column(name = "titulo",nullable = false, length = 20, unique = true)
    private String titulo;

    @Column(name = "fecha_Realizacion", length = 255)
    private LocalDate fechaRealizacion;

    @Column(name = "dimensiones", nullable = false, length = 255)
    private String dimensiones;

    @Column(name = "id_tecnica", nullable = false, length = 255)
    private String tecnicaId;

    @Column(name = "id_artistas", nullable = false, length = 255)
    private String artistaId;

    @Column(name = "precio", length = 1)
    private double precio;

    @Column(name = "cantidad_Visualizaciones", length = 15)
    private Integer cantidadVisualizaciones;
     
}

