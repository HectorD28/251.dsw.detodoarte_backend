// Repository
package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.CriterioEvaluacionesEconomicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterioEvaluacionesEconomicasRepository extends JpaRepository<CriterioEvaluacionesEconomicas, Long> {
}
