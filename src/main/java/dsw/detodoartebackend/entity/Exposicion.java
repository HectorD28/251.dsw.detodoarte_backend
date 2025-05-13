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
@Table(name="obras_exposicion")
public class Exposicion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exposicion")
    private Integer ExposicionId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false)
    private ObraDeArte ObraId ;  

}
