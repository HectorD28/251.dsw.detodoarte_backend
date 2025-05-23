/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.ObraDeArteRequest;
import dsw.detodoartebackend.dto.ObraDeArteResponse;
import dsw.detodoartebackend.service.ObraDeArteService;
import dsw.detodoartebackend.utils.ErrorResponse;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/obras")
public class ObraDeArteController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ObraDeArteService obradearteService;

    // Obtener todas las obras
    @GetMapping("/obtener")
    public ResponseEntity<?> obtenerTodasObras() {
        try {
            List<ObraDeArteResponse> obras = obradearteService.obtenerTodasObras();
            if (obras.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Crear nueva obra
    @PostMapping("/crear")
    public ResponseEntity<?> crearObra(@RequestBody ObraDeArteRequest obraRequest) {
        try {
            ObraDeArteResponse nuevaObra = obradearteService.guardarObra(obraRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaObra);
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
    public ResponseEntity<?> actualizarObra(@PathVariable Long id, @RequestBody ObraDeArteRequest obraRequest) {
        try {
            ObraDeArteResponse obraActualizada = obradearteService.actualizarObra(id, obraRequest);
            return ResponseEntity.ok(obraActualizada);
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
            obradearteService.eliminarObra(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Obtener obra por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerObraPorId(@PathVariable Long id) {
        try {
            ObraDeArteResponse obra = obradearteService.obtenerObraPorId(id);
            return ResponseEntity.ok(obra);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener obras por artista
    @GetMapping("/artista/{idArtista}")
    public ResponseEntity<?> obtenerObrasPorArtista(@PathVariable Long idArtista) {
        try {
            List<ObraDeArteResponse> obras = obradearteService.obtenerObrasPorArtista(idArtista);
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping("/{id}/imagen")
    public ResponseEntity<?> subirImagen(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            ObraDeArteResponse obraActualizada = obradearteService.actualizarRutaImagen(id, file);
            return ResponseEntity.ok(obraActualizada);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al subir la imagen: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }
}


