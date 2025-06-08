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
@Table(name="obras_de_arte")
public class ObraDeArte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obra")
    private Long obraId;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "fecha_realizacion")
    private LocalDate fechaRealizacion;

    @Column(name = "dimensiones")
    private String dimensiones;

    // Relación Muchos a Uno con la técnica
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tecnica", referencedColumnName = "id_tecnica", nullable = false)
    private Tecnica tecnica;

    // Relación Muchos a Uno con el artista
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_artista", referencedColumnName = "id_artista", nullable = false)
    private Artista artista;

    @Column(name = "precio")
    private double precio;

    @Column(name = "cantidad_visualizaciones")
    private Integer cantidadVisualizaciones;

    @Column(name = "ruta_imagen")
    private String rutaImagen;  // Ruta de la imagen de la obra

    @Column(name = "stock")
    private Integer stock;
    
    @Column(name = "estado_publicacion")
    private String estado_publicacion;
    
        public synchronized boolean reducirStock(int cantidad) {
        if (stock >= cantidad) {
            this.stock -= cantidad;
            return true;
        }
        return false;
    }

    public synchronized void restaurarStock(int cantidad) {
        this.stock += cantidad;
    }

    public synchronized void confirmarCompra(int cantidad) {
        if (this.stock >= cantidad) {
            this.stock -= cantidad;
        }
    }

    public synchronized void liberarStock(int cantidad) {
        this.restaurarStock(cantidad);
    }
    
}
