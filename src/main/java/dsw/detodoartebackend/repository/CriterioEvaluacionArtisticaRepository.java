package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.CriterioEvaluacionArtistica;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterioEvaluacionArtisticaRepository extends JpaRepository<CriterioEvaluacionArtistica, Long> {

    public List<CriterioEvaluacionArtistica> findByTecnica_IdTecnica(Long idTecnica);
}
