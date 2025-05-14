package dsw.detodoartebackend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dsw.detodoartebackend.dto.SolicitudExposicionRequest;
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

    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SolicitudObra> obras = new ArrayList<>(); // Relación 1:N
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaSolicitud = LocalDateTime.now();
    
    @Column(nullable = false)
    private String estadoSolicitud = "PENDIENTE";
    
    private String comentarios;

    public static SolicitudExposicion fromRequest(SolicitudExposicionRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromRequest'");
    }
}
