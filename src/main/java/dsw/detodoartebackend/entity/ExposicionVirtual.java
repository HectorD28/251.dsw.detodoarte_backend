package dsw.detodoartebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="exposiciones_virtuales")
public class ExposicionVirtual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exposicion_virtual")
    private Long idExposicionVirtual;

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    @Column(name = "estado_publicacion")
    private String estadoPublicacion;  // Pendiente, Publicada, etc.

    @Column(name = "url_publicacion")
    private String urlPublicacion;  // URL de la exposición virtual

    @Column(name = "comentarios")
    private String comentarios;  // Comentarios adicionales sobre la exposición
}
