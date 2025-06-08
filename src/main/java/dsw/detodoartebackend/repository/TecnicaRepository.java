package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.Tecnica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicaRepository extends JpaRepository<Tecnica, Long> {
    // Puedes añadir consultas personalizadas aquí si es necesario
}
