package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.EvaluacionEconomicaRequest;
import dsw.detodoartebackend.dto.EvaluacionEconomicaResponse;
import dsw.detodoartebackend.service.EvaluacionEconomicaService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones/economicas")
public class EvaluacionEconomicaController {

    @Autowired
    private EvaluacionEconomicaService evaluacionEconomicaService;

    @GetMapping("/obra/{idObra}")
    public ResponseEntity<?> getEvaluacionesPorObra(@PathVariable Long idObra) {
        try {
            List<EvaluacionEconomicaResponse> evaluaciones = evaluacionEconomicaService.getEvaluacionesPorObra(idObra);
            return ResponseEntity.ok(evaluaciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> createEvaluacion(@RequestBody EvaluacionEconomicaRequest evaluacionRequest) {
        try {
            EvaluacionEconomicaResponse nuevaEvaluacion = evaluacionEconomicaService.crearEvaluacion(evaluacionRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEvaluacion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    @PutMapping("/obra/{idObra}")
    public ResponseEntity<?> updateEvaluacion(@PathVariable Long idObra, @RequestBody EvaluacionEconomicaRequest evaluacionRequest) {
        try {
            EvaluacionEconomicaResponse updatedEvaluacion = evaluacionEconomicaService.actualizarEvaluacion(idObra, evaluacionRequest);
            return ResponseEntity.ok(updatedEvaluacion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    @DeleteMapping("/obra/{idObra}")
    public ResponseEntity<?> deleteEvaluacion(@PathVariable Long idObra) {
        try {
            evaluacionEconomicaService.eliminarEvaluacion(idObra);
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

