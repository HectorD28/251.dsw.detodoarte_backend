package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.SolicitudObraRequest;
import dsw.detodoartebackend.dto.SolicitudObraResponse;
import dsw.detodoartebackend.service.SolicitudObraService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes/obra")
public class SolicitudObraController {

    @Autowired
    private SolicitudObraService solicitudObraService;

    // Obtener todas las solicitudes de obras
    @GetMapping("/obtener")
    public ResponseEntity<?> getAllSolicitudes() {
        try {
            List<SolicitudObraResponse> solicitudes = solicitudObraService.getAllSolicitudes();
            return ResponseEntity.ok(solicitudes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Obtener solicitud de obra por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getSolicitudById(@PathVariable Long id) {
        try {
            SolicitudObraResponse solicitud = solicitudObraService.getSolicitudById(id);
            return ResponseEntity.ok(solicitud);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Crear nueva solicitud de obra
    @PostMapping("/crear")
    public ResponseEntity<?> createSolicitud(@RequestBody SolicitudObraRequest solicitudRequest) {
        try {
            SolicitudObraResponse nuevaSolicitud = solicitudObraService.createSolicitud(solicitudRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSolicitud);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Actualizar solicitud de obra
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSolicitud(@PathVariable Long id, @RequestBody SolicitudObraRequest solicitudRequest) {
        try {
            SolicitudObraResponse updatedSolicitud = solicitudObraService.updateSolicitud(id, solicitudRequest);
            return ResponseEntity.ok(updatedSolicitud);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Eliminar solicitud de obra
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSolicitud(@PathVariable Long id) {
        try {
            solicitudObraService.deleteSolicitud(id);
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
