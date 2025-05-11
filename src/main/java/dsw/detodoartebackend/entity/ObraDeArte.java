/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dsw.detodoartebackend.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "obras_de_Arte")
public class ObraDeArte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obra")
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "fecha_realizacion")
    private LocalDate fechaRealizacion;

    @Column(name = "dimensiones")
    private String dimensiones;

    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false)
    private Artista artista;  // Relación con la entidad Artista

    @ManyToOne
    @JoinColumn(name = "id_tecnica", nullable = false)
    private Tecnica tecnica;  // Relación con la entidad Tecnica

    @Column(name = "precio")
    private Double precio;

    @Column(name = "cantidad_visualizacines")
    private Integer cantidadVisualizaciones;

    @Lob
    @Column(name = "archivo")
    private byte[] archivo;  // Para almacenar la imagen de la obra
}
