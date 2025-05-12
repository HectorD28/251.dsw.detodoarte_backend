/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "especialistas")
@Data
public class Especialista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialista")
    private Long id;

    // Otros campos necesarios
}

