package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.SolicitudExposicionPresencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudExposicionPresencialRepository extends JpaRepository<SolicitudExposicionPresencial, Long> {
    // Métodos personalizados pueden ser añadidos aquí si es necesario
}
