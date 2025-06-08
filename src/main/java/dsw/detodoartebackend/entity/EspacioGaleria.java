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
@Table(name="espacios_galeria")
public class EspacioGaleria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_espacio")
    private Long idEspacio;

    @Column(name = "nombre_espacio")
    private String nombreEspacio;

    @Column(name = "descripcion")
    private String descripcion;  // Descripción del espacio (ejemplo: capacidad, ubicación, etc.)
}
