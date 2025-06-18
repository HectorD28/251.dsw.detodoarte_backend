package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.CriterioEvaluacionEconomicaRequest;
import dsw.detodoartebackend.dto.CriterioEvaluacionEconomicaResponse;
import dsw.detodoartebackend.service.CriterioEvaluacionEconomicaService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/criterios-evaluacion-economica")
public class CriterioEvaluacionEconomicaController {

    @Autowired
    private CriterioEvaluacionEconomicaService service;

    // Obtener todos los criterios
    @GetMapping("/obtener")
    public ResponseEntity<?> obtenerTodos() {
        try {
            List<CriterioEvaluacionEconomicaResponse> criterios = service.listarTodos();
            if (criterios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(criterios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Obtener criterio por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            CriterioEvaluacionEconomicaResponse criterio = service.getById(id);
            return ResponseEntity.ok(criterio);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Crear nuevo criterio
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody CriterioEvaluacionEconomicaRequest request) {
        try {
            CriterioEvaluacionEconomicaResponse nuevoCriterio = service.createCriterioEvaluacionEconomica(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCriterio);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Actualizar criterio
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody CriterioEvaluacionEconomicaRequest request) {
        try {
            CriterioEvaluacionEconomicaResponse criterioActualizado = service.updateCriterioEvaluacionEconomica(id, request);
            return ResponseEntity.ok(criterioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Eliminar criterio
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            service.deleteCriterioEvaluacionEconomica(id);
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
