package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.SolicitudObra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudObraRepository extends JpaRepository<SolicitudObra, Long> {
    // Métodos personalizados pueden ser añadidos aquí si es necesario
}
