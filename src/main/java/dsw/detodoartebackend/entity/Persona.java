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
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="personas")
public class Persona implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id")
    private Long personaId;
    
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
    
    @Column(name = "username", length = 50, unique = true)
    private String username;
    
    public Persona(String dni, String nombre_completo, String apellido_paterno,String apellido_materno,
            String direccion, char sexo, String telefono, String correoElectronico, String contrasena, String rol,boolean estado,String username) {
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
        this.estado = true; // Por defecto, el estado es verdadero (activo)
        this.username=username;
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
        if(personaRequest.getCorreoElectronico() != null) {
            this.correoElectronico = personaRequest.getCorreoElectronico();
        }
        if(personaRequest.getContrasena() != null) {
            this.contrasena = personaRequest.getContrasena();
        }
        if(personaRequest.getRol() != null) {
            this.rol = personaRequest.getRol();
        }
        this.estado = personaRequest.isEstado();
        if(personaRequest.getUsername() != null) {
            this.username = personaRequest.getUsername();
        }
        
    }
    
     @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Aquí devuelve la lista de roles/authorities del usuario
        // Por ejemplo, puedes crear un objeto SimpleGrantedAuthority con el rol
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.toUpperCase()));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return username; // usas el correo como username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // o implementa según estado
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // o implementa según estado
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // o implementa según estado
    }

    @Override
    public boolean isEnabled() {
        return estado; // si usas estado para habilitar/deshabilitar
    }
    
}


