
package dsw.detodoartebackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import dsw.detodoartebackend.entity.Personas;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonaRepository extends JpaRepository<Personas, Long> {

    boolean existsByDni(String dni);
    boolean existsByCorreoElectronico(String correoElectronico);
}
