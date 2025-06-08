package dsw.detodoartebackend.entity;

<<<<<<< HEAD
import jakarta.persistence.*;
=======
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
>>>>>>> 54190686d3925c64f2b8a30e6257b3af79f631ca
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="solicitud_obra")
public class SolicitudObra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud_obra")
<<<<<<< HEAD
    private Long idSolicitudObra;

    // Relación Muchos a Uno con SolicitudExposicionPresencial
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_solicitud_exposicion_presencial", referencedColumnName = "id_solicitud_exposicion_presencial", nullable = false)
    private SolicitudExposicionPresencial solicitudExposicionPresencial;

    // Relación Muchos a Uno con ObraDeArte
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false)
    private ObraDeArte obra;  // Relación con la tabla 'obras_de_arte'
=======
    private Long id_solicitud;
    
    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "id_especialista",referencedColumnName="id_especialista", unique = true)
    private Especialista especialista; 
    
    @Column(name = "estado_obra")
    private String estado_obra;
    
    @Column(name = "motivo_rechazo")
    private String motivo_rechazo;
    
    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "id_obra",referencedColumnName="id_obra", unique = true)
    private ObraDeArte obradearte; 
    
    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "id_solicitud_exposicion")
    private SolicitudExposicion solicitudExposicion;
>>>>>>> 54190686d3925c64f2b8a30e6257b3af79f631ca
}
