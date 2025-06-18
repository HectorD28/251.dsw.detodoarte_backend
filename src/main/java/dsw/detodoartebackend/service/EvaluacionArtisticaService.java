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

    public List<EvaluacionArtisticaResponse> getSolicitudesDeRevisionArtistica(Long idEspecialista) {
        // Obtener evaluaciones artísticas pendientes
        List<EvaluacionArtistica> evaluacionesArtisticas = evaluacionArtisticaRepository.findByEspecialista_IdEspecialista(idEspecialista);

        // Mapear las evaluaciones en el formato adecuado
        return EvaluacionArtisticaResponse.fromEntities(evaluacionesArtisticas);
    }

    // Método para obtener las evaluaciones de una obra específica
    public List<EvaluacionArtisticaResponse> getEvaluacionesPorObra(Long idObra) {
        List<EvaluacionArtistica> evaluaciones = evaluacionArtisticaRepository.findByObra_ObraId(idObra);
        return EvaluacionArtisticaResponse.fromEntities(evaluaciones);
    }

    // Método para crear una evaluación artística
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
                .puntajefinal(request.getPuntajefinal())
                .build();

        EvaluacionArtistica savedEvaluacion = evaluacionArtisticaRepository.save(evaluacion);
        return EvaluacionArtisticaResponse.fromEntity(savedEvaluacion);
    }

    // Método para actualizar la evaluación artística de una obra específica
    public EvaluacionArtisticaResponse actualizarEvaluacion(Long idObra, EvaluacionArtisticaRequest request) {
        // Buscar las evaluaciones relacionadas con la obra
        List<EvaluacionArtistica> evaluacionesExistentes = evaluacionArtisticaRepository.findByObra_ObraId(idObra);

        if (evaluacionesExistentes.isEmpty()) {
            throw new RuntimeException("No se encontró ninguna evaluación para la obra con ID " + idObra);
        }

        // Suponemos que solo hay una evaluación por obra y especialista
        EvaluacionArtistica evaluacionExistente = evaluacionesExistentes.get(0);

        // Verificar que la obra y el especialista existan
        ObraDeArte obra = obraDeArteRepository.findById(request.getIdObra())
                .orElseThrow(() -> new RuntimeException("Obra no encontrada"));

        Especialista especialista = especialistaRepository.findById(request.getIdEspecialista())
                .orElseThrow(() -> new RuntimeException("Especialista no encontrado"));

        evaluacionExistente.setObra(obra);
        evaluacionExistente.setEspecialista(especialista);
        evaluacionExistente.setFechaEvaluacion(request.getFechaEvaluacion());
        evaluacionExistente.setResultado(request.getResultado());
        evaluacionExistente.setMotivoRechazo(request.getMotivoRechazo());
        evaluacionExistente.setPuntajefinal(request.getPuntajefinal());

        EvaluacionArtistica updatedEvaluacion = evaluacionArtisticaRepository.save(evaluacionExistente);
        return EvaluacionArtisticaResponse.fromEntity(updatedEvaluacion);
    }

    // Método para eliminar la evaluación artística de una obra específica
    public void eliminarEvaluacion(Long idObra) {
        // Buscar las evaluaciones relacionadas con la obra
        List<EvaluacionArtistica> evaluacionesExistentes = evaluacionArtisticaRepository.findByObra_ObraId(idObra);

        if (evaluacionesExistentes.isEmpty()) {
            throw new RuntimeException("No se encontró ninguna evaluación para la obra con ID " + idObra);
        }

        // Suponemos que solo hay una evaluación por obra y especialista
        EvaluacionArtistica evaluacion = evaluacionesExistentes.get(0);

        evaluacionArtisticaRepository.delete(evaluacion);
    }
}

