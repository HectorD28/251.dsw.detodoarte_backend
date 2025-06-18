package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.CriterioEvaluacionesArtisticasRequest;
import dsw.detodoartebackend.dto.CriterioEvaluacionesArtisticasResponse;
import dsw.detodoartebackend.entity.CriterioEvaluacionesArtisticas;
import dsw.detodoartebackend.entity.CriterioEvaluacionArtistica;
import dsw.detodoartebackend.entity.EvaluacionArtistica;
import dsw.detodoartebackend.repository.CriterioEvaluacionesArtisticasRepository;
import dsw.detodoartebackend.repository.CriterioEvaluacionArtisticaRepository;
import dsw.detodoartebackend.repository.EvaluacionArtisticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CriterioEvaluacionesArtisticasService {

    @Autowired
    private CriterioEvaluacionesArtisticasRepository repository;

    @Autowired
    private EvaluacionArtisticaRepository evaluacionArtisticaRepository;

    @Autowired
    private CriterioEvaluacionArtisticaRepository criterioEvaluacionRepository;

    /*---------------------------  READ  ---------------------------*/
    public List<CriterioEvaluacionesArtisticasResponse> listarTodosCriterioEvaluacionesArtisticas() {
        return repository.findAll().stream()
                .map(CriterioEvaluacionesArtisticasResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    public CriterioEvaluacionesArtisticasResponse getById(Long id) {
        CriterioEvaluacionesArtisticas entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Criterio de evaluación artística no encontrado con ID " + id));

        return CriterioEvaluacionesArtisticasResponse.fromEntity(entity);
    }
    
    /*---------------------------  CREATE  ---------------------------*/
    public CriterioEvaluacionesArtisticasResponse crearCriterioEvaluacionesArtisticas(CriterioEvaluacionesArtisticasRequest request) {
        EvaluacionArtistica evaluacion = evaluacionArtisticaRepository.findById(request.getId_evaluacion_artistica())
                .orElseThrow(() -> new RuntimeException("Evaluación artística no encontrada"));

        CriterioEvaluacionArtistica criterio = criterioEvaluacionRepository.findById(request.getId_criterio_evaluacion_artistica())
                .orElseThrow(() -> new RuntimeException("Criterio de evaluación no encontrado"));

        CriterioEvaluacionesArtisticas nuevo = CriterioEvaluacionesArtisticas.builder()
                .evaluacionArtistica(evaluacion)
                .criterioEvaluacionArtisitica(criterio)
                .puntajeCriterio(request.getPuntaje_criterio())
                .build();

        return CriterioEvaluacionesArtisticasResponse.fromEntity(repository.save(nuevo));
    }
    
    /*---------------------------  UPDATE  ---------------------------*/
    public CriterioEvaluacionesArtisticasResponse updateCriterioEvaluacionesArtisticas(
            Long id, CriterioEvaluacionesArtisticasRequest req) {

        CriterioEvaluacionesArtisticas entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Criterio de evaluación artística no encontrado con ID " + id));

        // Si se envía un id_tecnica, validar y asignar.
        if (req.getId_criterio_evaluacion_artistica() != null) {
            CriterioEvaluacionArtistica criterioEvaluacionArtistica = criterioEvaluacionRepository.findById(req.getId_criterio_evaluacion_artistica())
                    .orElseThrow(() -> new RuntimeException(
                            "Técnica no encontrada con ID " + req.getId_criterio_evaluacion_artistica()));
            entity.setCriterioEvaluacionArtisitica(criterioEvaluacionArtistica);
        }
        
                // Si se envía un id_tecnica, validar y asignar.
        if (req.getId_evaluacion_artistica() != null) {
            EvaluacionArtistica evaluacionArtistica = evaluacionArtisticaRepository.findById(req.getId_evaluacion_artistica())
                    .orElseThrow(() -> new RuntimeException(
                            "Técnica no encontrada con ID " + req.getId_evaluacion_artistica()));
            entity.setEvaluacionArtistica(evaluacionArtistica);
        }
        
        entity.setPuntajeCriterio(req.getPuntaje_criterio());

        return CriterioEvaluacionesArtisticasResponse.fromEntity(
                repository.save(entity)
        );
    }

    /*---------------------------  DELETE  ---------------------------*/
    public void deleteCriterioEvaluacionesArtisticas(Long id) {
        CriterioEvaluacionesArtisticas entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Criterio de evaluaciónes artísticas no encontrado con ID " + id));

        repository.delete(entity);
    }

}