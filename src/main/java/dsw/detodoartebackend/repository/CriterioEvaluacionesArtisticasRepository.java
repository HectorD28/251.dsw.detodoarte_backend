package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.CriterioEvaluacionesArtisticas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterioEvaluacionesArtisticasRepository extends JpaRepository<CriterioEvaluacionesArtisticas, Long> {
        // Método para verificar si ya existe una combinación de id_evaluacion_artistica e id_criterio_evaluacion_artistica
    boolean existsByEvaluacionArtistica_IdEvaluacionArtisticaAndCriterioEvaluacionArtistica_IdCriterioEvaluacionArtistica(
            Long idEvaluacionArtistica, Long idCriterioEvaluacionArtistica);
}