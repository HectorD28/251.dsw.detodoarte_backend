package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.TecnicaRequest;
import dsw.detodoartebackend.dto.TecnicaResponse;
import dsw.detodoartebackend.service.TecnicaService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tecnicas")
public class TecnicaController {

    @Autowired
    private TecnicaService tecnicaService;

    // Obtener todas las técnicas
    @GetMapping("/obtener")
    public ResponseEntity<?> getAllTecnicas() {
        try {
            List<TecnicaResponse> tecnicas = tecnicaService.getAllTecnicas();
            if (tecnicas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(tecnicas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Obtener técnica por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getTecnicaById(@PathVariable Long id) {
        try {
            TecnicaResponse tecnica = tecnicaService.getTecnicaById(id);
            return ResponseEntity.ok(tecnica);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Crear nueva técnica
    @PostMapping("/crear")
    public ResponseEntity<?> createTecnica(@RequestBody TecnicaRequest tecnicaRequest) {
        try {
            TecnicaResponse nuevaTecnica = tecnicaService.createTecnica(tecnicaRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTecnica);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Actualizar técnica
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTecnica(@PathVariable Long id, @RequestBody TecnicaRequest tecnicaRequest) {
        try {
            TecnicaResponse updatedTecnica = tecnicaService.updateTecnica(id, tecnicaRequest);
            return ResponseEntity.ok(updatedTecnica);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Eliminar técnica
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTecnica(@PathVariable Long id) {
        try {
            tecnicaService.deleteTecnica(id);
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
