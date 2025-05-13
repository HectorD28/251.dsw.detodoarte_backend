package dsw.detodoartebackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "solicitud_obra")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SolicitudObraId.class)
public class SolicitudObra {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_solicitud")
    private SolicitudExposicion solicitud;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "id_obra")
    private ObraDeArte obra;
    
    //@ManyToOne
    //@JoinColumn(name = "id_especialista")
    //private Especialista especialista;
    
    private String estadoObra = "PENDIENTE";
}
