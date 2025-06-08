package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.ExposicionPresencialRequest;
import dsw.detodoartebackend.dto.ExposicionPresencialResponse;
import dsw.detodoartebackend.service.ExposicionPresencialService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exposiciones/presenciales")
public class ExposicionPresencialController {

    @Autowired
    private ExposicionPresencialService exposicionService;

    // Obtener todas las exposiciones
    @GetMapping("/obtener")
    public ResponseEntity<?> getAllExposiciones() {
        try {
            List<ExposicionPresencialResponse> exposiciones = exposicionService.getAllExposiciones();
            return ResponseEntity.ok(exposiciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Obtener exposición por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getExposicionById(@PathVariable Long id) {
        try {
            ExposicionPresencialResponse exposicion = exposicionService.getExposicionById(id);
            return ResponseEntity.ok(exposicion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Crear nueva exposición
    @PostMapping("/crear")
    public ResponseEntity<?> createExposicion(@RequestBody ExposicionPresencialRequest exposicionRequest) {
        try {
            ExposicionPresencialResponse nuevaExposicion = exposicionService.createExposicion(exposicionRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaExposicion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Actualizar exposición
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExposicion(@PathVariable Long id, @RequestBody ExposicionPresencialRequest exposicionRequest) {
        try {
            ExposicionPresencialResponse updatedExposicion = exposicionService.updateExposicion(id, exposicionRequest);
            return ResponseEntity.ok(updatedExposicion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Eliminar exposición
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExposicion(@PathVariable Long id) {
        try {
            exposicionService.deleteExposicion(id);
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
