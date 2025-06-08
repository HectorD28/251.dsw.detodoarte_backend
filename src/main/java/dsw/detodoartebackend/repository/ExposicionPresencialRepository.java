package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.ExposicionPresencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExposicionPresencialRepository extends JpaRepository<ExposicionPresencial, Long> {
    // Métodos personalizados pueden ser añadidos aquí si es necesario
}
