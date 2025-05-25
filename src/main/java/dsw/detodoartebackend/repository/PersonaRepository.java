
package dsw.detodoartebackend.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import dsw.detodoartebackend.entity.Personas;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface PersonaRepository extends JpaRepository<Personas, Long> {
    UserDetails findByUsername(String username);
    boolean existsByDni(String dni);
    boolean existsByCorreoElectronico(String correoElectronico);
    List<Personas> findByEstadoTrue();
    Optional<Personas> findByDni(Long dni);
    Optional<Personas> findByDniAndEstadoTrue(Long dni);


}
