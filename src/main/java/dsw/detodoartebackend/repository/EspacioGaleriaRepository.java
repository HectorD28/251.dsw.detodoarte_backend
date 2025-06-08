package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.EspacioGaleria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspacioGaleriaRepository extends JpaRepository<EspacioGaleria, Long> {
    // Métodos personalizados pueden ser añadidos aquí si es necesario
}
