package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.AdministradorRequest;
import dsw.detodoartebackend.dto.AdministradorResponse;
import dsw.detodoartebackend.service.AdministradorService;
import dsw.detodoartebackend.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    // Obtener todos los administradores
    @GetMapping("/obtener")
    public ResponseEntity<?> getAllAdministradores() {
        try {
            List<AdministradorResponse> administradores = administradorService.getAllAdministradores();
            return ResponseEntity.ok(administradores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Obtener administrador por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdministradorById(@PathVariable Long id) {
        try {
            AdministradorResponse administrador = administradorService.getAdministradorById(id);
            return ResponseEntity.ok(administrador);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Crear nuevo administrador
    @PostMapping("/crear")
    public ResponseEntity<?> createAdministrador(@RequestBody AdministradorRequest administradorRequest) {
        try {
            AdministradorResponse nuevoAdministrador = administradorService.createAdministrador(administradorRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAdministrador);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Actualizar administrador
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdministrador(@PathVariable Long id, @RequestBody AdministradorRequest administradorRequest) {
        try {
            AdministradorResponse updatedAdministrador = administradorService.updateAdministrador(id, administradorRequest);
            return ResponseEntity.ok(updatedAdministrador);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado");
        }
    }

    // Eliminar administrador
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdministrador(@PathVariable Long id) {
        try {
            administradorService.deleteAdministrador(id);
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
