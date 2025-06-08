package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    // Métodos personalizados pueden ser añadidos aquí si es necesario
}
