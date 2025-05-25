//RICHARD 
package dsw.detodoartebackend.entity;
import dsw.detodoartebackend.dto.PersonaRequest;
//richard
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
public class Personas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id")
    private Long persona_id;
    
    @Column(name = "dni",nullable = false, length = 20, unique = true)
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
    private char sexo;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "correo_electronico", nullable = false, length = 100, unique = true)
    private String correoElectronico;
    
    @Column(name = "contrasena", nullable = false, length = 100, unique = false)
    private String contrasena;

    @Column(name = "rol", length = 20)
    private String rol;

    @Column(name = "estado")
    private boolean estado;

    
    public Personas(String dni, String nombre_completo, String apellido_paterno,String apellido_materno,
            String direccion, char sexo, String telefono, String correoElectronico, String contrasena, String rol) {
        this.dni = dni;
        this.nombreCompleto = nombre_completo;
        this.apellidoPaterno = apellido_paterno;
        this.apellidoMaterno = apellido_materno;
        this.direccion = direccion;
        this.sexo = sexo;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.rol = rol;
        this.estado = false; // Por defecto, el estado es verdadero (activo)
    }

    public void desacticarCliente() {
     this.estado=false;
}

    public void actualizarDatos(PersonaRequest personaRequest) {

        if(personaRequest.getDni() != null) {
            this.dni = personaRequest.getDni();
        }
        if(personaRequest.getNombre_completo() != null) {
            this.nombreCompleto = personaRequest.getNombre_completo();
        }
        if(personaRequest.getApellido_paterno() != null) {
            this.apellidoPaterno = personaRequest.getApellido_paterno();
        }
        if(personaRequest.getApellido_materno() != null) {
            this.apellidoMaterno = personaRequest.getApellido_materno();
        }
        if(personaRequest.getDireccion_residencia() != null) {
            this.direccion = personaRequest.getDireccion_residencia();
        }
        if(personaRequest.getSexo() != '\0') {
            this.sexo = personaRequest.getSexo();
        }
        if(personaRequest.getTelefono() != null) {
            this.telefono = personaRequest.getTelefono();
        }
        if(personaRequest.getCorreo_electronico() != null) {
            this.correoElectronico = personaRequest.getCorreo_electronico();
        }
        if(personaRequest.getContrasena() != null) {
            this.contrasena = personaRequest.getContrasena();
        }
        if(personaRequest.getRol() != null) {
            this.rol = personaRequest.getRol();
        }
        
    }
}


