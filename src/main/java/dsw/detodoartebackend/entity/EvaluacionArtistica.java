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
import jakarta.persistence.OneToOne;
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
@Table(name="evaluaciones_artisticas")
public class EvaluacionArtistica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluacion_artistica")
    private Long idEvaluacionArtistica;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id_solicitud", nullable = false)
    private SolicitudExposicion solicitudExposicion;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false)
    private ObraDeArte obradearte;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_especialista", referencedColumnName = "id_especialista", nullable = false)
    private Especialista especialista;
    
    @Column(name = "fecha_evaluacion")
    private LocalDate fechaEvaluacion;
    
    @Column(name = "resultado")
    private String resultado;
    
    @Column(name = "motivo_rechazo")
    private String motivoRechazo;

}
