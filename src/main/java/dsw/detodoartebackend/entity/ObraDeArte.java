
package dsw.detodoartebackend.entity;

import jakarta.persistence.*;
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
    private Integer ObraId;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "fecha_realizacion")
    private LocalDate fechaRealizacion;

    @Column(name = "dimensiones")
    private String dimensiones;

    // Relación Muchos a Uno con la técnica
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tecnica", referencedColumnName = "id_tecnica", nullable = false)
    private Tecnica tecnica;  // La relación con la tabla 'tecnicas'

    // Relación Muchos a Uno con el artista
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_artista", referencedColumnName = "id_artista", nullable = false)
    private Artista artista;  // La relación con la tabla 'artistas'

    @Column(name = "precio")
    private double precio;

    @Column(name = "cantidad_visualizacines")
    private Integer cantidadVisualizaciones;

}


