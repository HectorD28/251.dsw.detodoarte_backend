package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.EvaluacionEconomica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluacionEconomicaRepository extends JpaRepository<EvaluacionEconomica, Long> {
    List<EvaluacionEconomica> findByObra_IdObra(Long idObra);
}
