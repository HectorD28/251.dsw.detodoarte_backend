package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.CriterioEvaluacionArtisticaRequest;
import dsw.detodoartebackend.dto.CriterioEvaluacionArtisticaResponse;
import dsw.detodoartebackend.service.CriterioEvaluacionArtisticaService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/criterios-evaluacion-artisticos")
@CrossOrigin(origins = "*")
public class CriterioEvaluacionArtisticaController {

    @Autowired
    private CriterioEvaluacionArtisticaService criterioService;

    // ---------------------- Crear ----------------------
    @PostMapping()
    public ResponseEntity<?> crearCriterio(@RequestBody CriterioEvaluacionArtisticaRequest request) {
        try {
            CriterioEvaluacionArtisticaResponse response = criterioService.createCriterioEvaluacionArtistica(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ErrorResponse.builder().message("Error al crear criterio de evaluación artística: " + e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ErrorResponse.builder().message("Error inesperado: " + e.getMessage()).build());
        }
    }

    // ---------------------- Listar Todos ----------------------
    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            List<CriterioEvaluacionArtisticaResponse> criterios = criterioService.getAllCriterioEvaluacionArtistica();
            if (criterios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(criterios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ErrorResponse.builder().message("Error al listar criterios de evaluación artística: " + e.getMessage()).build());
        }
    }

    // ---------------------- Buscar por ID ----------------------
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            CriterioEvaluacionArtisticaResponse criterio = criterioService.getById(id);
            return ResponseEntity.ok(criterio);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ErrorResponse.builder().message("Criterio de evaluación artística no encontrado con ID " + id).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ErrorResponse.builder().message("Error al obtener criterio de evaluación artística: " + e.getMessage()).build());
        }
    }

    // ---------------------- Buscar por Técnica ----------------------
    @GetMapping("/tecnica/{idTecnica}")
    public ResponseEntity<?> obtenerPorTecnica(@PathVariable Long idTecnica) {
        try {
            List<CriterioEvaluacionArtisticaResponse> criterios = criterioService.getCriteriosByTecnica(idTecnica);
            return ResponseEntity.ok(criterios);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ErrorResponse.builder().message("Criterios de evaluación artística no encontrados para la técnica con ID " + idTecnica).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ErrorResponse.builder().message("Error al obtener criterios de evaluación artística para la técnica con ID " + idTecnica + ": " + e.getMessage()).build());
        }
    }

    // ---------------------- Actualizar ----------------------
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCriterio(@PathVariable Long id, @RequestBody CriterioEvaluacionArtisticaRequest request) {
        try {
            CriterioEvaluacionArtisticaResponse response = criterioService.updateCriterioEvaluacionArtistica(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ErrorResponse.builder().message("Error al actualizar criterio de evaluación artística: " + e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ErrorResponse.builder().message("Error inesperado: " + e.getMessage()).build());
        }
    }

    // ---------------------- Eliminar ----------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCriterio(@PathVariable Long id) {
        try {
            criterioService.deleteCriterioEvaluacionArtistica(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ErrorResponse.builder().message("Criterio de evaluación artística no encontrado con ID " + id).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ErrorResponse.builder().message("Error al eliminar criterio de evaluación artística: " + e.getMessage()).build());
        }
    }
}
