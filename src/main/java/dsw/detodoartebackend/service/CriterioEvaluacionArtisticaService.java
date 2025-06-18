package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.CriterioEvaluacionArtisticaRequest;
import dsw.detodoartebackend.dto.CriterioEvaluacionArtisticaResponse;
import dsw.detodoartebackend.entity.CriterioEvaluacionArtistica;
import dsw.detodoartebackend.entity.Tecnica;
import dsw.detodoartebackend.repository.CriterioEvaluacionArtisticaRepository;
import dsw.detodoartebackend.repository.TecnicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CriterioEvaluacionArtisticaService {

    @Autowired
    private CriterioEvaluacionArtisticaRepository criterioRepo;

    @Autowired
    private TecnicaRepository tecnicaRepo;

    /*---------------------------  CREATE  ---------------------------*/
    public CriterioEvaluacionArtisticaResponse createCriterioEvaluacionArtistica(CriterioEvaluacionArtisticaRequest req) {

        Tecnica tecnica = tecnicaRepo.findById(req.getId_tecnica())
                .orElseThrow(() -> new RuntimeException("Técnica no encontrada con ID " + req.getId_tecnica()));

        CriterioEvaluacionArtistica entity = CriterioEvaluacionArtistica.builder()
                .tecnica(tecnica)
                .nombreCriterio(req.getNombre_criterio())
                .descripcion(req.getDescripcion())
                .build();

        return CriterioEvaluacionArtisticaResponse.fromEntity(
                criterioRepo.save(entity)
        );
    }

    /*---------------------------  READ  ---------------------------*/
    public List<CriterioEvaluacionArtisticaResponse> getAllCriterioEvaluacionArtistica() {
        return criterioRepo.findAll()
                .stream()
                .map(CriterioEvaluacionArtisticaResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public CriterioEvaluacionArtisticaResponse getById(Long id) {
        CriterioEvaluacionArtistica entity = criterioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Criterio de evaluación artística no encontrado con ID " + id));

        return CriterioEvaluacionArtisticaResponse.fromEntity(entity);
    }

    /*---------------------------  UPDATE  ---------------------------*/
    public CriterioEvaluacionArtisticaResponse updateCriterioEvaluacionArtistica(
            Long id, CriterioEvaluacionArtisticaRequest req) {

        CriterioEvaluacionArtistica entity = criterioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Criterio de evaluación artística no encontrado con ID " + id));

        // Si se envía un id_tecnica, validar y asignar.
        if (req.getId_tecnica() != null) {
            Tecnica tecnica = tecnicaRepo.findById(req.getId_tecnica())
                    .orElseThrow(() -> new RuntimeException(
                            "Técnica no encontrada con ID " + req.getId_tecnica()));
            entity.setTecnica(tecnica);
        }

        entity.setNombreCriterio(req.getNombre_criterio());
        entity.setDescripcion(req.getDescripcion());

        return CriterioEvaluacionArtisticaResponse.fromEntity(
                criterioRepo.save(entity)
        );
    }

    /*---------------------------  DELETE  ---------------------------*/
    public void deleteCriterioEvaluacionArtistica(Long id) {
        CriterioEvaluacionArtistica entity = criterioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Criterio de evaluación artística no encontrado con ID " + id));

        criterioRepo.delete(entity);
    }
    
    /*---------------------------  GET CRITERIOS POR TÉCNICA  ---------------------------*/
    public List<CriterioEvaluacionArtisticaResponse> getCriteriosByTecnica(Long idTecnica) {
        List<CriterioEvaluacionArtistica> criterios = criterioRepo.findByTecnica_IdTecnica(idTecnica);
        if (criterios.isEmpty()) {
            throw new RuntimeException("No se encontraron criterios para la técnica con ID " + idTecnica);
        }
        return criterios.stream()
                .map(CriterioEvaluacionArtisticaResponse::fromEntity)
                .collect(Collectors.toList());
    }
}

