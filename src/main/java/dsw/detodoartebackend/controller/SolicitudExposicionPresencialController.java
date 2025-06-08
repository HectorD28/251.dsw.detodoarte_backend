package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.SolicitudExposicionPresencialRequest;
import dsw.detodoartebackend.dto.SolicitudExposicionPresencialResponse;
import dsw.detodoartebackend.service.SolicitudExposicionPresencialService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes/exposicion")
public class SolicitudExposicionPresencialController {

    @Autowired
    private SolicitudExposicionPresencialService solicitudService;

    // Obtener todas las solicitudes
    @GetMapping("/obtener")
    public ResponseEntity<?> getAllSolicitudes() {
        try {
            List<SolicitudExposicionPresencialResponse> solicitudes = solicitudService.getAllSolicitudes();
            return ResponseEntity.ok(solicitudes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Obtener solicitud por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getSolicitudById(@PathVariable Long id) {
        try {
            SolicitudExposicionPresencialResponse solicitud = solicitudService.getSolicitudById(id);
            return ResponseEntity.ok(solicitud);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Crear nueva solicitud de exposición
    @PostMapping("/crear")
    public ResponseEntity<?> createSolicitud(@RequestBody SolicitudExposicionPresencialRequest solicitudRequest) {
        try {
            SolicitudExposicionPresencialResponse nuevaSolicitud = solicitudService.createSolicitud(solicitudRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSolicitud);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Actualizar solicitud de exposición
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSolicitud(@PathVariable Long id, @RequestBody SolicitudExposicionPresencialRequest solicitudRequest) {
        try {
            SolicitudExposicionPresencialResponse updatedSolicitud = solicitudService.updateSolicitud(id, solicitudRequest);
            return ResponseEntity.ok(updatedSolicitud);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Eliminar solicitud de exposición
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSolicitud(@PathVariable Long id) {
        try {
            solicitudService.deleteSolicitud(id);
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
