package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {
    // Métodos personalizados pueden ser añadidos aquí si es necesario
}
