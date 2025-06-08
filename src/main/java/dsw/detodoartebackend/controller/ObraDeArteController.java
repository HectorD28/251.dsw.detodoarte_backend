package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.ObraDeArteRequest;
import dsw.detodoartebackend.dto.ObraDeArteResponse;
import dsw.detodoartebackend.service.ObraDeArteService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/obras")
public class ObraDeArteController {

    @Autowired
    private ObraDeArteService obraDeArteService;

    // Obtener todas las obras
    @GetMapping("/obtener")
    public ResponseEntity<?> obtenerTodasObras() {
        try {
            List<ObraDeArteResponse> obras = obraDeArteService.obtenerTodasObras();
            if (obras.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Obtener obra por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerObraPorId(@PathVariable Long id) {
        try {
            ObraDeArteResponse obra = obraDeArteService.obtenerObraPorId(id);
            return ResponseEntity.ok(obra);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Obtener obras por artista
    @GetMapping("/artista/{idArtista}")
    public ResponseEntity<?> obtenerObrasPorArtista(@PathVariable Long idArtista) {
        try {
            List<ObraDeArteResponse> obras = obraDeArteService.obtenerObrasPorArtista(idArtista);
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Crear nueva obra
    @PostMapping("/crear")
    public ResponseEntity<?> crearObra(@RequestBody ObraDeArteRequest obraRequest) {
        try {
            ObraDeArteResponse nuevaObra = obraDeArteService.guardarObra(obraRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaObra);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Actualizar obra
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarObra(@PathVariable Long id, @RequestBody ObraDeArteRequest obraRequest) {
        try {
            ObraDeArteResponse updatedObra = obraDeArteService.actualizarObra(id, obraRequest);
            return ResponseEntity.ok(updatedObra);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Eliminar obra
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarObra(@PathVariable Long id) {
        try {
            obraDeArteService.eliminarObra(id);
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
