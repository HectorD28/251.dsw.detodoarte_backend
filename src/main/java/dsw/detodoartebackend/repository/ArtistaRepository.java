
package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long>{

}
