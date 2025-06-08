package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.EvaluacionEspacioRequest;
import dsw.detodoartebackend.dto.EvaluacionEspacioResponse;
import dsw.detodoartebackend.entity.EvaluacionEspacio;
import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.entity.EspacioGaleria;
import dsw.detodoartebackend.entity.Administrador;
import dsw.detodoartebackend.repository.EvaluacionEspacioRepository;
import dsw.detodoartebackend.repository.ObraDeArteRepository;
import dsw.detodoartebackend.repository.EspacioGaleriaRepository;
import dsw.detodoartebackend.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluacionEspacioService {

    @Autowired
    private EvaluacionEspacioRepository evaluacionEspacioRepository;

    @Autowired
    private ObraDeArteRepository obraDeArteRepository;

    @Autowired
    private EspacioGaleriaRepository espacioGaleriaRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    public List<EvaluacionEspacioResponse> getEvaluacionesPorObra(Long idObra) {
        List<EvaluacionEspacio> evaluaciones = evaluacionEspacioRepository.findByObra_IdObra(idObra);
        return EvaluacionEspacioResponse.fromEntities(evaluaciones);
    }

    public EvaluacionEspacioResponse crearEvaluacion(EvaluacionEspacioRequest request) {
        ObraDeArte obra = obraDeArteRepository.findById(request.getIdObra())
                .orElseThrow(() -> new RuntimeException("Obra no encontrada"));

        EspacioGaleria espacio = espacioGaleriaRepository.findById(request.getIdEspacio())
                .orElseThrow(() -> new RuntimeException("Espacio no encontrado"));

        Administrador administrador = administradorRepository.findById(request.getIdAdministrador())
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        EvaluacionEspacio evaluacion = EvaluacionEspacio.builder()
                .obra(obra)
                .espacioGaleria(espacio)
                .administrador(administrador)
                .resultado(request.getResultado())
                .comentarios(request.getComentarios())
                .build();

        EvaluacionEspacio savedEvaluacion = evaluacionEspacioRepository.save(evaluacion);
        return EvaluacionEspacioResponse.fromEntity(savedEvaluacion);
    }

    public EvaluacionEspacioResponse updateEvaluacion(Long id, EvaluacionEspacioRequest request) {
        EvaluacionEspacio existingEvaluacion = evaluacionEspacioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada con ID " + id));

        ObraDeArte obra = obraDeArteRepository.findById(request.getIdObra())
                .orElseThrow(() -> new RuntimeException("Obra no encontrada"));

        EspacioGaleria espacio = espacioGaleriaRepository.findById(request.getIdEspacio())
                .orElseThrow(() -> new RuntimeException("Espacio no encontrado"));

        Administrador administrador = administradorRepository.findById(request.getIdAdministrador())
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        existingEvaluacion.setObra(obra);
        existingEvaluacion.setEspacioGaleria(espacio);
        existingEvaluacion.setAdministrador(administrador);
        existingEvaluacion.setResultado(request.getResultado());
        existingEvaluacion.setComentarios(request.getComentarios());

        EvaluacionEspacio updatedEvaluacion = evaluacionEspacioRepository.save(existingEvaluacion);
        return EvaluacionEspacioResponse.fromEntity(updatedEvaluacion);
    }

    public void deleteEvaluacion(Long id) {
        EvaluacionEspacio evaluacion = evaluacionEspacioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada con ID " + id));
        evaluacionEspacioRepository.delete(evaluacion);
    }
}
