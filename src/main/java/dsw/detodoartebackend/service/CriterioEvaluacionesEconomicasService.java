/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.CriterioEvaluacionesEconomicasRequest;
import dsw.detodoartebackend.dto.CriterioEvaluacionesEconomicasResponse;
import dsw.detodoartebackend.entity.CriterioEvaluacionEconomica;
import dsw.detodoartebackend.entity.CriterioEvaluacionesEconomicas;
import dsw.detodoartebackend.entity.EvaluacionEconomica;
import dsw.detodoartebackend.repository.CriterioEvaluacionEconomicaRepository;
import dsw.detodoartebackend.repository.CriterioEvaluacionesEconomicasRepository;
import dsw.detodoartebackend.repository.EvaluacionEconomicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CriterioEvaluacionesEconomicasService {

    @Autowired
    private CriterioEvaluacionesEconomicasRepository repository;

    @Autowired
    private EvaluacionEconomicaRepository evaluacionEconomicaRepository;

    @Autowired
    private CriterioEvaluacionEconomicaRepository criterioEvaluacionEconomicaRepository;

    /*---------------------------  READ  ---------------------------*/
    public List<CriterioEvaluacionesEconomicasResponse> listarTodosCriterioEvaluacionesEconomicas() {
        return repository.findAll().stream()
                .map(CriterioEvaluacionesEconomicasResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    public CriterioEvaluacionesEconomicasResponse getById(Long id) {
        CriterioEvaluacionesEconomicas entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Criterio de evaluación artística no encontrado con ID " + id));

        return CriterioEvaluacionesEconomicasResponse.fromEntity(entity);
    }
    
    /*---------------------------  CREATE  ---------------------------*/
    public CriterioEvaluacionesEconomicasResponse crearCriterioEvaluacionesEconomicas(CriterioEvaluacionesEconomicasRequest request) {
        EvaluacionEconomica evaluacion = evaluacionEconomicaRepository.findById(request.getId_evaluacion_economica())
                .orElseThrow(() -> new RuntimeException("Evaluación artística no encontrada"));

        CriterioEvaluacionEconomica criterio = criterioEvaluacionEconomicaRepository.findById(request.getId_criterio_evaluacion_economica())
                .orElseThrow(() -> new RuntimeException("Criterio de evaluación no encontrado"));

        CriterioEvaluacionesEconomicas nuevo = CriterioEvaluacionesEconomicas.builder()
                .evaluacionEconomica(evaluacion)
                .criterioEvaluacionEconomica(criterio)
                .precioCriterio(request.getPrecio_criterio())
                .build();

        return CriterioEvaluacionesEconomicasResponse.fromEntity(repository.save(nuevo));
    }
    
    /*---------------------------  UPDATE  ---------------------------*/
    public CriterioEvaluacionesEconomicasResponse updateCriterioEvaluacionesEconomicas(Long id, CriterioEvaluacionesEconomicasRequest req) {

        CriterioEvaluacionesEconomicas entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Criterio de evaluación Rconomicas no encontrado con ID " + id));

       
        if (req.getId_criterio_evaluacion_economica()!= null) {
            CriterioEvaluacionEconomica criterioEvaluacionEconomica = criterioEvaluacionEconomicaRepository.findById(req.getId_criterio_evaluacion_economica())
                    .orElseThrow(() -> new RuntimeException(
                            "Técnica no encontrada con ID " + req.getId_criterio_evaluacion_economica()));
            entity.setCriterioEvaluacionEconomica(criterioEvaluacionEconomica);
        }
        
                // Si se envía un id_tecnica, validar y asignar.
        if (req.getId_evaluacion_economica() != null) {
            EvaluacionEconomica evaluacionEconomica = evaluacionEconomicaRepository.findById(req.getId_evaluacion_economica())
                    .orElseThrow(() -> new RuntimeException(
                            "Técnica no encontrada con ID " + req.getId_evaluacion_economica()));
            entity.setEvaluacionEconomica(evaluacionEconomica);
        }
        
        entity.setPrecioCriterio(req.getPrecio_criterio());

        return CriterioEvaluacionesEconomicasResponse.fromEntity(
                repository.save(entity)
        );
    }

    /*---------------------------  DELETE  ---------------------------*/
    public void deleteCriterioEvaluacionesEconomicas(Long id) {
        CriterioEvaluacionesEconomicas entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Criterio de evaluaciónes artísticas no encontrado con ID " + id));

        repository.delete(entity);
    }

}