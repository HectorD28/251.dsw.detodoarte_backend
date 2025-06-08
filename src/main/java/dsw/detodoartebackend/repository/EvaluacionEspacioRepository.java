package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.EvaluacionEspacio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluacionEspacioRepository extends JpaRepository<EvaluacionEspacio, Long> {

    // Agregar este método para buscar evaluaciones de espacio por ID de la obra
    List<EvaluacionEspacio> findByObra_IdObra(Long idObra);

}
