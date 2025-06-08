
package dsw.detodoartebackend.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import dsw.detodoartebackend.entity.Persona;
import org.springframework.stereotype.Repository;
import java.util.Optional;



@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByUsername(String username);

    boolean existsByDni(String dni);
    boolean existsByCorreoElectronico(String correoElectronico);
    List<Persona> findByEstadoTrue();
    Optional<Persona> findByDni(String dni);
    Optional<Persona> findByDniAndEstadoTrue(String dni);
}
