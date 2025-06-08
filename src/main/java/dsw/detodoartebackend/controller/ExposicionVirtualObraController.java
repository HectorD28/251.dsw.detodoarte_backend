package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.ExposicionVirtualObraRequest;
import dsw.detodoartebackend.dto.ExposicionVirtualObraResponse;
import dsw.detodoartebackend.service.ExposicionVirtualObraService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exposiciones/virtuales/obras")
public class ExposicionVirtualObraController {

    @Autowired
    private ExposicionVirtualObraService exposicionVirtualObraService;

    // Obtener todas las exposiciones virtuales con obras asociadas
    @GetMapping("/obtener")
    public ResponseEntity<?> getAllExposicionesConObras() {
        try {
            List<ExposicionVirtualObraResponse> exposicionesObras = exposicionVirtualObraService.getAllExposicionesConObras();
            return ResponseEntity.ok(exposicionesObras);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Crear una nueva asociación entre exposición virtual y obra
    @PostMapping("/crear")
    public ResponseEntity<?> createExposicionVirtualObra(@RequestBody ExposicionVirtualObraRequest request) {
        try {
            ExposicionVirtualObraResponse nuevaExposicionVirtualObra = exposicionVirtualObraService.createExposicionVirtualObra(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaExposicionVirtualObra);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Eliminar una asociación entre exposición virtual y obra
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExposicionVirtualObra(@PathVariable Long id) {
        try {
            exposicionVirtualObraService.deleteExposicionVirtualObra(id);
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
