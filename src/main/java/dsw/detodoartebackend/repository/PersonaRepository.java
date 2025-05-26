
package dsw.detodoartebackend.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import dsw.detodoartebackend.entity.Personas;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface PersonaRepository extends JpaRepository<Personas, Long> {
    @Query("SELECT p FROM Personas p WHERE p.correoElectronico = :username")
    UserDetails findByCorreoElectronico(@Param("correo_electronico") String correoElectronico);
    boolean existsByDni(String dni);
    boolean existsByCorreoElectronico(String correoElectronico);
    List<Personas> findByEstadoTrue();
    Optional<Personas> findByDni(String dni);
    Optional<Personas> findByDniAndEstadoTrue(String dni);


}
