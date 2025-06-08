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
    private Long idEspecialista;

    // Relación Muchos a Uno con Persona
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "persona_id", referencedColumnName = "persona_id", nullable = false)
    private Persona persona;  // Relación con la tabla 'personas'

    // Relación Muchos a Uno con la Técnica
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tecnica", referencedColumnName = "id_tecnica", nullable = false)
    private Tecnica tecnica;  // Relación con la tabla 'tecnicas'
}
