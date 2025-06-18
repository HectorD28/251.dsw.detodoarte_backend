package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.CriterioEvaluacionEconomica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterioEvaluacionEconomicaRepository extends JpaRepository<CriterioEvaluacionEconomica, Long> {
}