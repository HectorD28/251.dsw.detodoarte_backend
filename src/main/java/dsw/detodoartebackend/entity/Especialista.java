/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
@Table(name="especialistas")
public class Especialista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialista")
    private Long id_especialista;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "persona_id",referencedColumnName="persona_id", unique = true)
    private Personas persona; 
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tecnica",referencedColumnName="id_tecnica", nullable = false)
    private Tecnica tecnica; 
    
}

