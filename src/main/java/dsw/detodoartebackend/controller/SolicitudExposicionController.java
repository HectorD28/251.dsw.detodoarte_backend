package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.SolicitudExposicionRequest;
import dsw.detodoartebackend.dto.SolicitudExposicionResponse;
import dsw.detodoartebackend.entity.SolicitudExposicion;
import dsw.detodoartebackend.service.SolicitudExposicionService;
import dsw.detodoartebackend.utils.ErrorResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/solicitudexposicion")
public class SolicitudExposicionController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SolicitudExposicionService solicitudexposcionService;

    // Obtener todas las exposiciones
    @GetMapping("/obtener")
    public ResponseEntity<?> obtenerTodasObras() {
        try {
            List<SolicitudExposicionResponse> solicitudesexposiciones = solicitudexposcionService.obtenerTodasSolicitudesExposicion();
            if (solicitudesexposiciones.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(solicitudesexposiciones);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Crear nueva obra
    @PostMapping("/crear")
    public ResponseEntity<?> crearObra(@RequestBody SolicitudExposicionRequest solicitudexposicionRequest) {
        try {
            SolicitudExposicionResponse nuevaSolicitudExposicion = solicitudexposcionService.guardarSolicitudExposicion(solicitudexposicionRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSolicitudExposicion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Actualizar obra
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarObra(@PathVariable Long id, @RequestBody SolicitudExposicionRequest solicitudexposicionRequest) {
        try {
            SolicitudExposicionResponse solicitudexposicionActualizada = solicitudexposcionService.actualizarSolicitudExposicion(id, solicitudexposicionRequest);
            return ResponseEntity.ok(solicitudexposicionActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Eliminar obra
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarObra(@PathVariable Long id) {
        try {
            solicitudexposcionService.eliminarSolicitudExposicion(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Obtener Solicitud Exposicion por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerObraPorId(@PathVariable Long id) {
        try {
            SolicitudExposicionResponse solicitudexposicion = solicitudexposcionService.obtenerSolicitudesExposicionPorId(id);
            return ResponseEntity.ok(solicitudexposicion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener solicitudes Exposicion por artista
    @GetMapping("/artista/{idArtista}")
    public ResponseEntity<?> obtenerSolicitudExposicionPorArtista(@PathVariable Long idsolicitudexposicion) {
        try {
            List<SolicitudExposicionResponse> solicitudexposicion = solicitudexposcionService.obtenerSolcitudExposicionPorArtista(idsolicitudexposicion);
            return ResponseEntity.ok(solicitudexposicion);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}