/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="evaluacion_parametro")
public class EvalucionParametro {
      
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parametro")
    private Long idParametrp;
    
    @Column(name = "puntaje")
    private Integer puntaje;
    
    @Column(name = "observacion")
    private String observacion;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_evaluacion_artistica", referencedColumnName = "id_evaluacion_artistica", nullable = false)
    private EvaluacionArtistica evaluacionArtistica;
    
}
