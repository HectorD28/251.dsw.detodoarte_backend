package dsw.detodoartebackend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "solicitudes_exposicion")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudExposicion {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitud;
    
    @OneToOne
    @JoinColumn(name = "id_artista", nullable = false)
    private Artista artista;

    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL)
    private List<SolicitudObra> obras; // Relación 1:N
    
    @Column(nullable = false, updatable = false)
    private Date fechaSolicitud;
    
    @Column(nullable = false)
    private String estadoSolicitud = "PENDIENTE";
    
    private String comentarios;
}
