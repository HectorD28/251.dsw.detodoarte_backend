package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.EvaluacionEconomicaRequest;
import dsw.detodoartebackend.dto.EvaluacionEconomicaResponse;
import dsw.detodoartebackend.entity.EvaluacionEconomica;
import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.entity.Especialista;
import dsw.detodoartebackend.repository.EvaluacionEconomicaRepository;
import dsw.detodoartebackend.repository.ObraDeArteRepository;
import dsw.detodoartebackend.repository.EspecialistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluacionEconomicaService {

    @Autowired
    private EvaluacionEconomicaRepository evaluacionEconomicaRepository;

    @Autowired
    private ObraDeArteRepository obraDeArteRepository;

    @Autowired
    private EspecialistaRepository especialistaRepository;

    public List<EvaluacionEconomicaResponse> getEvaluacionesPorObra(Long idObra) {
        List<EvaluacionEconomica> evaluaciones = evaluacionEconomicaRepository.findByObra_ObraId(idObra);
        return EvaluacionEconomicaResponse.fromEntities(evaluaciones);
    }

    public EvaluacionEconomicaResponse crearEvaluacion(EvaluacionEconomicaRequest request) {
        ObraDeArte obra = obraDeArteRepository.findById(request.getIdObra())
                .orElseThrow(() -> new RuntimeException("Obra no encontrada"));

        Especialista especialista = especialistaRepository.findById(request.getIdEspecialista())
                .orElseThrow(() -> new RuntimeException("Especialista no encontrado"));

        EvaluacionEconomica evaluacion = EvaluacionEconomica.builder()
                .obra(obra)
                .especialista(especialista)
                .precioVenta(request.getPrecioVenta())
                .porcentajeGanancia(request.getPorcentajeGanancia())
                .fechaEvaluacion(request.getFechaEvaluacion())
                .resultado(request.getResultado())
                .motivoRechazo(request.getMotivoRechazo())
                .build();

        EvaluacionEconomica savedEvaluacion = evaluacionEconomicaRepository.save(evaluacion);
        return EvaluacionEconomicaResponse.fromEntity(savedEvaluacion);
    }

    public EvaluacionEconomicaResponse actualizarEvaluacion(Long id, EvaluacionEconomicaRequest request) {
        EvaluacionEconomica evaluacionExistente = evaluacionEconomicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada con ID " + id));

        ObraDeArte obra = obraDeArteRepository.findById(request.getIdObra())
                .orElseThrow(() -> new RuntimeException("Obra no encontrada"));

        Especialista especialista = especialistaRepository.findById(request.getIdEspecialista())
                .orElseThrow(() -> new RuntimeException("Especialista no encontrado"));

        evaluacionExistente.setObra(obra);
        evaluacionExistente.setEspecialista(especialista);
        evaluacionExistente.setPrecioVenta(request.getPrecioVenta());
        evaluacionExistente.setPorcentajeGanancia(request.getPorcentajeGanancia());
        evaluacionExistente.setFechaEvaluacion(request.getFechaEvaluacion());
        evaluacionExistente.setResultado(request.getResultado());
        evaluacionExistente.setMotivoRechazo(request.getMotivoRechazo());

        EvaluacionEconomica updatedEvaluacion = evaluacionEconomicaRepository.save(evaluacionExistente);
        return EvaluacionEconomicaResponse.fromEntity(updatedEvaluacion);
    }

    public void eliminarEvaluacion(Long id) {
        EvaluacionEconomica evaluacion = evaluacionEconomicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada con ID " + id));
        evaluacionEconomicaRepository.delete(evaluacion);
    }
}
