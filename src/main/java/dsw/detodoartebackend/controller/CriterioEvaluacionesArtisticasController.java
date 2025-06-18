package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.CriterioEvaluacionesArtisticasRequest;
import dsw.detodoartebackend.dto.CriterioEvaluacionesArtisticasResponse;
import dsw.detodoartebackend.service.CriterioEvaluacionesArtisticasService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/criterios-evaluaciones-artisticas")
public class CriterioEvaluacionesArtisticasController {

    @Autowired
    private CriterioEvaluacionesArtisticasService service;

    // Obtener todos los criterios de evaluaciones artísticas
    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        try {
            List<CriterioEvaluacionesArtisticasResponse> criterios = service.listarTodosCriterioEvaluacionesArtisticas();
            if (criterios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(criterios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Obtener criterio de evaluación artística por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            CriterioEvaluacionesArtisticasResponse criterio = service.getById(id);
            return ResponseEntity.ok(criterio);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Crear nuevo criterio de evaluación artística
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody CriterioEvaluacionesArtisticasRequest request) {
        try {
            CriterioEvaluacionesArtisticasResponse nuevoCriterio = service.crearCriterioEvaluacionesArtisticas(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCriterio);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Actualizar criterio de evaluación artística
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody CriterioEvaluacionesArtisticasRequest request) {
        try {
            CriterioEvaluacionesArtisticasResponse criterioActualizado = service.updateCriterioEvaluacionesArtisticas(id, request);
            return ResponseEntity.ok(criterioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Eliminar criterio de evaluación artística
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            service.deleteCriterioEvaluacionesArtisticas(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }
}
