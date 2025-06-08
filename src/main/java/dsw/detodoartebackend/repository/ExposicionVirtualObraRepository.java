package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.ExposicionVirtualObra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExposicionVirtualObraRepository extends JpaRepository<ExposicionVirtualObra, Long> {
    // Métodos personalizados pueden ser añadidos aquí si es necesario
}
