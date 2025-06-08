package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.EvaluacionEspacioRequest;
import dsw.detodoartebackend.dto.EvaluacionEspacioResponse;
import dsw.detodoartebackend.service.EvaluacionEspacioService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones/espacio")
public class EvaluacionEspacioController {

    @Autowired
    private EvaluacionEspacioService evaluacionEspacioService;

    // Obtener evaluaciones de espacio por obra
    @GetMapping("/obra/{idObra}")
    public ResponseEntity<?> getEvaluacionesPorObra(@PathVariable Long idObra) {
        try {
            List<EvaluacionEspacioResponse> evaluaciones = evaluacionEspacioService.getEvaluacionesPorObra(idObra);
            return ResponseEntity.ok(evaluaciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Crear nueva evaluación de espacio
    @PostMapping("/crear")
    public ResponseEntity<?> createEvaluacion(@RequestBody EvaluacionEspacioRequest evaluacionRequest) {
        try {
            EvaluacionEspacioResponse nuevaEvaluacion = evaluacionEspacioService.crearEvaluacion(evaluacionRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEvaluacion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Actualizar evaluación de espacio
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvaluacion(@PathVariable Long id, @RequestBody EvaluacionEspacioRequest evaluacionRequest) {
        try {
            EvaluacionEspacioResponse updatedEvaluacion = evaluacionEspacioService.updateEvaluacion(id, evaluacionRequest);
            return ResponseEntity.ok(updatedEvaluacion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Eliminar evaluación de espacio
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvaluacion(@PathVariable Long id) {
        try {
            evaluacionEspacioService.deleteEvaluacion(id);
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
