package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.ArtistaRequest;
import dsw.detodoartebackend.dto.ArtistaResponse;
import dsw.detodoartebackend.service.ArtistaService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    // Obtener todos los artistas
    @GetMapping("/obtener")
    public ResponseEntity<?> getAllArtistas() {
        try {
            List<ArtistaResponse> artistas = artistaService.getAllArtistas();
            if (artistas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(artistas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Obtener artista por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getArtistaById(@PathVariable Long id) {
        try {
            ArtistaResponse artista = artistaService.getArtistaById(id);
            return ResponseEntity.ok(artista);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Crear nuevo artista
    @PostMapping("/crear")
    public ResponseEntity<?> createArtista(@RequestBody ArtistaRequest artistaRequest) {
        try {
            ArtistaResponse nuevoArtista = artistaService.createArtista(artistaRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoArtista);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Actualizar artista
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateArtista(@PathVariable Long id, @RequestBody ArtistaRequest artistaRequest) {
        try {
            ArtistaResponse updatedArtista = artistaService.updateArtista(id, artistaRequest);
            return ResponseEntity.ok(updatedArtista);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Eliminar artista
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtista(@PathVariable Long id) {
        try {
            artistaService.deleteArtista(id);
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
