 package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.SolicitudExposicion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudExposicionRepository extends JpaRepository<SolicitudExposicion, Long>{
    
    List<SolicitudExposicion> findByArtista_IdArtista(Long id_artista);
    List<SolicitudExposicion> findByEstadoSolicitud(String estadoSolicitud);

}
