
package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.Especialista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EspecialistaRepository extends JpaRepository<Especialista, Long>{
    
}
