package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.CriterioEvaluacionEconomicaRequest;
import dsw.detodoartebackend.dto.CriterioEvaluacionEconomicaResponse;
import dsw.detodoartebackend.entity.CriterioEvaluacionEconomica;
import dsw.detodoartebackend.entity.Tecnica;
import dsw.detodoartebackend.repository.CriterioEvaluacionEconomicaRepository;
import dsw.detodoartebackend.repository.TecnicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CriterioEvaluacionEconomicaService {

    @Autowired
    private CriterioEvaluacionEconomicaRepository repository;

    @Autowired
    private TecnicaRepository tecnicaRepository;

    /*---------------------------  READ  ---------------------------*/
    public List<CriterioEvaluacionEconomicaResponse> listarTodos() {
        return repository.findAll().stream()
                .map(CriterioEvaluacionEconomicaResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    public CriterioEvaluacionEconomicaResponse getById(Long id) {
        CriterioEvaluacionEconomica entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Criterio de evaluación económica no encontrado con ID " + id));

        return CriterioEvaluacionEconomicaResponse.fromEntity(entity);
    }

    /*---------------------------  CREATE  ---------------------------*/
    public CriterioEvaluacionEconomicaResponse createCriterioEvaluacionEconomica(CriterioEvaluacionEconomicaRequest request) {
        Tecnica tecnica = tecnicaRepository.findById(request.getId_tecnica())
                .orElseThrow(() -> new RuntimeException("Técnica no encontrada"));

        CriterioEvaluacionEconomica nuevo = CriterioEvaluacionEconomica.builder()
                .tecnica(tecnica)
                .nombreCriterio(request.getNombre_criterio())
                .descripcion(request.getDescripcion())
                .build();

        return CriterioEvaluacionEconomicaResponse.fromEntity(repository.save(nuevo));
    }
    
    /*---------------------------  UPDATE  ---------------------------*/
    public CriterioEvaluacionEconomicaResponse updateCriterioEvaluacionEconomica(
            Long id, CriterioEvaluacionEconomicaRequest req) {

        CriterioEvaluacionEconomica entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Criterio de evaluación económica no encontrado con ID " + id));

        // Si se envía un id_tecnica, validar y asignar.
        if (req.getId_tecnica() != null) {
            Tecnica tecnica = tecnicaRepository.findById(req.getId_tecnica())
                    .orElseThrow(() -> new RuntimeException(
                            "Técnica no encontrada con ID " + req.getId_tecnica()));
            entity.setTecnica(tecnica);
        }

        entity.setNombreCriterio(req.getNombre_criterio());
        entity.setDescripcion(req.getDescripcion());

        return CriterioEvaluacionEconomicaResponse.fromEntity(
                repository.save(entity)
        );
    }
    
    /*---------------------------  DELETE  ---------------------------*/
    public void deleteCriterioEvaluacionEconomica(Long id) {
        CriterioEvaluacionEconomica entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Criterio de evaluación económica no encontrado con ID " + id));

        repository.delete(entity);
    }
}
