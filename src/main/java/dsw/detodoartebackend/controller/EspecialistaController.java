package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.EspecialistaRequest;
import dsw.detodoartebackend.dto.EspecialistaResponse;
import dsw.detodoartebackend.service.EspecialistaService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialistas")
public class EspecialistaController {

    @Autowired
    private EspecialistaService especialistaService;

    // Obtener todos los especialistas
    @GetMapping("/obtener")
    public ResponseEntity<?> getAllEspecialistas() {
        try {
            List<EspecialistaResponse> especialistas = especialistaService.getAllEspecialistas();
            if (especialistas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(especialistas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Obtener especialista por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getEspecialistaById(@PathVariable Long id) {
        try {
            EspecialistaResponse especialista = especialistaService.getEspecialistaById(id);
            return ResponseEntity.ok(especialista);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Crear nuevo especialista
    @PostMapping("/crear")
    public ResponseEntity<?> createEspecialista(@RequestBody EspecialistaRequest especialistaRequest) {
        try {
            EspecialistaResponse nuevoEspecialista = especialistaService.createEspecialista(especialistaRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEspecialista);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Actualizar especialista
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEspecialista(@PathVariable Long id, @RequestBody EspecialistaRequest especialistaRequest) {
        try {
            EspecialistaResponse updatedEspecialista = especialistaService.updateEspecialista(id, especialistaRequest);
            return ResponseEntity.ok(updatedEspecialista);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Eliminar especialista
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEspecialista(@PathVariable Long id) {
        try {
            especialistaService.deleteEspecialista(id);
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
