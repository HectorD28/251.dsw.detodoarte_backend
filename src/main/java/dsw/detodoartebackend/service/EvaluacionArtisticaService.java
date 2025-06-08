package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.EvaluacionArtisticaRequest;
import dsw.detodoartebackend.dto.EvaluacionArtisticaResponse;
import dsw.detodoartebackend.entity.EvaluacionArtistica;
import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.entity.Especialista;
import dsw.detodoartebackend.repository.EvaluacionArtisticaRepository;
import dsw.detodoartebackend.repository.ObraDeArteRepository;
import dsw.detodoartebackend.repository.EspecialistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluacionArtisticaService {

    @Autowired
    private EvaluacionArtisticaRepository evaluacionArtisticaRepository;

    @Autowired
    private ObraDeArteRepository obraDeArteRepository;

    @Autowired
    private EspecialistaRepository especialistaRepository;

    public List<EvaluacionArtisticaResponse> getEvaluacionesPorObra(Long idObra) {
        List<EvaluacionArtistica> evaluaciones = evaluacionArtisticaRepository.findByObra_ObraId(idObra);
        return EvaluacionArtisticaResponse.fromEntities(evaluaciones);
    }

    public EvaluacionArtisticaResponse crearEvaluacion(EvaluacionArtisticaRequest request) {
        ObraDeArte obra = obraDeArteRepository.findById(request.getIdObra())
                .orElseThrow(() -> new RuntimeException("Obra no encontrada"));

        Especialista especialista = especialistaRepository.findById(request.getIdEspecialista())
                .orElseThrow(() -> new RuntimeException("Especialista no encontrado"));

        EvaluacionArtistica evaluacion = EvaluacionArtistica.builder()
                .obra(obra)
                .especialista(especialista)
                .fechaEvaluacion(request.getFechaEvaluacion())
                .resultado(request.getResultado())
                .motivoRechazo(request.getMotivoRechazo())
                .build();

        EvaluacionArtistica savedEvaluacion = evaluacionArtisticaRepository.save(evaluacion);
        return EvaluacionArtisticaResponse.fromEntity(savedEvaluacion);
    }

    public EvaluacionArtisticaResponse actualizarEvaluacion(Long id, EvaluacionArtisticaRequest request) {
        EvaluacionArtistica evaluacionExistente = evaluacionArtisticaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada con ID " + id));

        ObraDeArte obra = obraDeArteRepository.findById(request.getIdObra())
                .orElseThrow(() -> new RuntimeException("Obra no encontrada"));

        Especialista especialista = especialistaRepository.findById(request.getIdEspecialista())
                .orElseThrow(() -> new RuntimeException("Especialista no encontrado"));

        evaluacionExistente.setObra(obra);
        evaluacionExistente.setEspecialista(especialista);
        evaluacionExistente.setFechaEvaluacion(request.getFechaEvaluacion());
        evaluacionExistente.setResultado(request.getResultado());
        evaluacionExistente.setMotivoRechazo(request.getMotivoRechazo());

        EvaluacionArtistica updatedEvaluacion = evaluacionArtisticaRepository.save(evaluacionExistente);
        return EvaluacionArtisticaResponse.fromEntity(updatedEvaluacion);
    }

    public void eliminarEvaluacion(Long id) {
        EvaluacionArtistica evaluacion = evaluacionArtisticaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada con ID " + id));
        evaluacionArtisticaRepository.delete(evaluacion);
    }
}
