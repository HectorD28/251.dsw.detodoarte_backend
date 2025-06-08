package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.ExposicionVirtual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExposicionVirtualRepository extends JpaRepository<ExposicionVirtual, Long> {
    // Métodos personalizados pueden ser añadidos aquí si es necesario
}
