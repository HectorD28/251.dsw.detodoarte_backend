package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.EvaluacionArtistica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluacionArtisticaRepository extends JpaRepository<EvaluacionArtistica, Long> {
    List<EvaluacionArtistica> findByObra_ObraId(Long obraId);
}
