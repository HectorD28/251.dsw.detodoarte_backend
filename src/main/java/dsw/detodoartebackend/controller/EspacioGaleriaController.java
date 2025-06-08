package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.EspacioGaleriaRequest;
import dsw.detodoartebackend.dto.EspacioGaleriaResponse;
import dsw.detodoartebackend.service.EspacioGaleriaService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/espacios/galeria")
public class EspacioGaleriaController {

    @Autowired
    private EspacioGaleriaService espacioGaleriaService;

    // Obtener todos los espacios de galería
    @GetMapping("/obtener")
    public ResponseEntity<?> getAllEspacios() {
        try {
            List<EspacioGaleriaResponse> espacios = espacioGaleriaService.getAllEspacios();
            return ResponseEntity.ok(espacios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Obtener espacio de galería por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getEspacioById(@PathVariable Long id) {
        try {
            EspacioGaleriaResponse espacio = espacioGaleriaService.getEspacioById(id);
            return ResponseEntity.ok(espacio);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Crear nuevo espacio de galería
    @PostMapping("/crear")
    public ResponseEntity<?> createEspacio(@RequestBody EspacioGaleriaRequest espacioRequest) {
        try {
            EspacioGaleriaResponse nuevoEspacio = espacioGaleriaService.createEspacio(espacioRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEspacio);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Actualizar espacio de galería
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEspacio(@PathVariable Long id, @RequestBody EspacioGaleriaRequest espacioRequest) {
        try {
            EspacioGaleriaResponse updatedEspacio = espacioGaleriaService.updateEspacio(id, espacioRequest);
            return ResponseEntity.ok(updatedEspacio);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Eliminar espacio de galería
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEspacio(@PathVariable Long id) {
        try {
            espacioGaleriaService.deleteEspacio(id);
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
