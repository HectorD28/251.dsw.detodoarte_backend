package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.CriterioEvaluacionesEconomicasRequest;
import dsw.detodoartebackend.dto.CriterioEvaluacionesEconomicasResponse;
import dsw.detodoartebackend.service.CriterioEvaluacionesEconomicasService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/criterios-evaluaciones-economicas")
public class CriterioEvaluacionesEconomicasController {

    @Autowired
    private CriterioEvaluacionesEconomicasService service;

    // Obtener todos los criterios de evaluaciones económicas
    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        try {
            List<CriterioEvaluacionesEconomicasResponse> criterios = service.listarTodosCriterioEvaluacionesEconomicas();
            if (criterios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(criterios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Obtener criterio de evaluación económica por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            CriterioEvaluacionesEconomicasResponse criterio = service.getById(id);
            return ResponseEntity.ok(criterio);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Crear nuevo criterio de evaluación económica
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody CriterioEvaluacionesEconomicasRequest request) {
        try {
            CriterioEvaluacionesEconomicasResponse nuevoCriterio = service.crearCriterioEvaluacionesEconomicas(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCriterio);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Actualizar criterio de evaluación económica
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody CriterioEvaluacionesEconomicasRequest request) {
        try {
            CriterioEvaluacionesEconomicasResponse criterioActualizado = service.updateCriterioEvaluacionesEconomicas(id, request);
            return ResponseEntity.ok(criterioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Eliminar criterio de evaluación económica
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            service.deleteCriterioEvaluacionesEconomicas(id);
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
