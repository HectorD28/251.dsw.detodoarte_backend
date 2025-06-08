package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.EvaluacionArtisticaRequest;
import dsw.detodoartebackend.dto.EvaluacionArtisticaResponse;
import dsw.detodoartebackend.service.EvaluacionArtisticaService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones/artisticas")
public class EvaluacionArtisticaController {

    @Autowired
    private EvaluacionArtisticaService evaluacionArtisticaService;

    // Obtener evaluaciones por obra
    @GetMapping("/obra/{idObra}")
    public ResponseEntity<?> getEvaluacionesPorObra(@PathVariable Long idObra) {
        try {
            List<EvaluacionArtisticaResponse> evaluaciones = evaluacionArtisticaService.getEvaluacionesPorObra(idObra);
            return ResponseEntity.ok(evaluaciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Crear nueva evaluación artística
    @PostMapping("/crear")
    public ResponseEntity<?> createEvaluacion(@RequestBody EvaluacionArtisticaRequest evaluacionRequest) {
        try {
            EvaluacionArtisticaResponse nuevaEvaluacion = evaluacionArtisticaService.crearEvaluacion(evaluacionRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEvaluacion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Actualizar evaluación artística
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvaluacion(@PathVariable Long id, @RequestBody EvaluacionArtisticaRequest evaluacionRequest) {
        try {
            EvaluacionArtisticaResponse updatedEvaluacion = evaluacionArtisticaService.actualizarEvaluacion(id, evaluacionRequest);
            return ResponseEntity.ok(updatedEvaluacion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Eliminar evaluación artística
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvaluacion(@PathVariable Long id) {
        try {
            evaluacionArtisticaService.eliminarEvaluacion(id);
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
