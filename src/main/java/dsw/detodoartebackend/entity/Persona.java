
package dsw.detodoartebackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long personaId;
    
    @Column(nullable = false, length = 100)
    private Long dni;
    
    @Column(name="nombre_completo")
    private Long nombre;
    
    @Column(name="apellido_paterno")
    private String apellidoPaterno;
    
    @Column(name="direccion_residencia")
    private String direccion;
    
    @Column(name="telefono")
    private String telefono;
    
    @Column(name="sexo")
    private String sexo;
    
    private String nombres;
    
    @Column(name="fecha_nacimiento")
    private Date fechaNacimiento;
            
            
}


