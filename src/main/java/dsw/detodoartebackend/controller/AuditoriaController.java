package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.AuditoriaRequest;
import dsw.detodoartebackend.dto.AuditoriaResponse;
import dsw.detodoartebackend.service.AuditoriaService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditoria")
public class AuditoriaController {

    @Autowired
    private AuditoriaService auditoriaService;

    // Obtener todas las auditorías
    @GetMapping("/obtener")
    public ResponseEntity<?> getAllAuditorias() {
        try {
            List<AuditoriaResponse> auditorias = auditoriaService.getAllAuditorias();
            return ResponseEntity.ok(auditorias);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Obtener auditoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAuditoriaById(@PathVariable Long id) {
        try {
            AuditoriaResponse auditoria = auditoriaService.getAuditoriaById(id);
            return ResponseEntity.ok(auditoria);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Registrar un nuevo registro en la auditoría
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarAuditoria(@RequestBody AuditoriaRequest auditoriaRequest) {
        try {
            auditoriaService.registrarAuditoria(auditoriaRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Actualizar auditoría
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuditoria(@PathVariable Long id, @RequestBody AuditoriaRequest auditoriaRequest) {
        try {
            AuditoriaResponse updatedAuditoria = auditoriaService.updateAuditoria(id, auditoriaRequest);
            return ResponseEntity.ok(updatedAuditoria);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Eliminar auditoría
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuditoria(@PathVariable Long id) {
        try {
            auditoriaService.deleteAuditoria(id);
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
