package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.SolicitudExposicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudExposicionRepository extends JpaRepository<SolicitudExposicion, Long>{
    
}
