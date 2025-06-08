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
@Table(name="administradores")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_administrador")
    private Long idAdministrador;

    // Relación Muchos a Uno con Persona
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "persona_id", referencedColumnName = "persona_id", nullable = false)
    private Persona persona;  // Relación con la tabla 'personas'

    @Column(name = "password")
    private String password;

    @Column(name = "tipo_usuario")
    private String tipoUsuario;  // Por ejemplo: 'admin', 'moderador'
}
