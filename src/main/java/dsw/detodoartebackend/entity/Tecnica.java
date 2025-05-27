/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "tecnicas")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tecnica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tecnica")
    private Long idTecnica;

    @Column(name = "nombre_tecnica")
    private String nombreTecnica;
}


