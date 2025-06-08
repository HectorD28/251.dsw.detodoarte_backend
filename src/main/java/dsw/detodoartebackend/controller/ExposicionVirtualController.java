package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.ExposicionVirtualRequest;
import dsw.detodoartebackend.dto.ExposicionVirtualResponse;
import dsw.detodoartebackend.service.ExposicionVirtualService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exposiciones/virtuales")
public class ExposicionVirtualController {

    @Autowired
    private ExposicionVirtualService exposicionVirtualService;

    // Obtener todas las exposiciones virtuales
    @GetMapping("/obtener")
    public ResponseEntity<?> getAllExposiciones() {
        try {
            List<ExposicionVirtualResponse> exposiciones = exposicionVirtualService.getAllExposiciones();
            return ResponseEntity.ok(exposiciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Obtener exposición virtual por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getExposicionById(@PathVariable Long id) {
        try {
            ExposicionVirtualResponse exposicion = exposicionVirtualService.getExposicionById(id);
            return ResponseEntity.ok(exposicion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Crear nueva exposición virtual
    @PostMapping("/crear")
    public ResponseEntity<?> createExposicion(@RequestBody ExposicionVirtualRequest exposicionRequest) {
        try {
            ExposicionVirtualResponse nuevaExposicion = exposicionVirtualService.createExposicion(exposicionRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaExposicion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Actualizar exposición virtual
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExposicion(@PathVariable Long id, @RequestBody ExposicionVirtualRequest exposicionRequest) {
        try {
            ExposicionVirtualResponse updatedExposicion = exposicionVirtualService.updateExposicion(id, exposicionRequest);
            return ResponseEntity.ok(updatedExposicion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Eliminar exposición virtual
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExposicion(@PathVariable Long id) {
        try {
            exposicionVirtualService.deleteExposicion(id);
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
