//RICHARD 
package dsw.detodoartebackend.entity;
//richard
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="personas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id")
    private Long personaId;
    
    @Column(nullable = false, length = 20, unique = true)
    private String dni;

    @Column(name = "nombre_completo", nullable = false, length = 255)
    private String nombreCompleto;

    @Column(name = "apellido_paterno", nullable = false, length = 255)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false, length = 255)
    private String apellidoMaterno;

    @Column(name = "direccion_residencia", length = 255)
    private String direccion;

    @Column(name = "sexo", length = 1)
    private String sexo;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "correo_electronico", nullable = false, length = 100, unique = true)
    private String correoElectronico;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;            
            
}


