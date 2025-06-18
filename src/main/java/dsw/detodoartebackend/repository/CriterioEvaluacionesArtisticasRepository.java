package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.CriterioEvaluacionesArtisticas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterioEvaluacionesArtisticasRepository extends JpaRepository<CriterioEvaluacionesArtisticas, Long> {
}