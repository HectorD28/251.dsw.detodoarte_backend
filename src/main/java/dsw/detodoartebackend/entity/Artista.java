/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity  // Anotar como @Entity para que sea reconocida como una entidad JPA
@Data
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artista")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Personas persona;  // Relación con la entidad Persona

    // Otros campos adicionales si los tienes
}

