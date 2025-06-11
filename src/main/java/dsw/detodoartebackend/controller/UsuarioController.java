package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.PersonaResponse;
import dsw.detodoartebackend.dto.UsuarioResponse;
import dsw.detodoartebackend.entity.Personas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dsw.detodoartebackend.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    // Aquí puedes implementar los endpoints necesarios para manejar las operaciones relacionadas con los usuarios.
    // Por ejemplo, métodos para crear, actualizar, eliminar y obtener usuarios.
    // Ejemplo de endpoint para listar todos los usuarios
    @GetMapping("/listar")
    public ResponseEntity<?> listarUsuarios() {
        try {
            List<PersonaResponse> usuarios = usuarioService.listarUsuariosActivos();
            List<UsuarioResponse> usuariosResponse = usuarios.stream()
                    .map(usuario -> new UsuarioResponse(
                            usuario.getNombreCompleto(),
                            usuario.getApellidoPaterno(),
                            usuario.getApellidoMaterno(),
                            usuario.getDni(),
                            usuario.getCorreoElectronico(),
                            usuario.getRol(),
                            usuario.getTelefono()))
                    .toList();
            return ResponseEntity.ok(usuariosResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron usuarios");
        } 
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado al listar usuarios");
        }
    }

    // Ejemplo de endpoint para obtener un usuario por DNI
    @GetMapping("/buscar/{dni}")
    public ResponseEntity<?> obtenerUsuarioPorDni(@PathVariable String dni) {
        try {
            PersonaResponse usuario = usuarioService.obtenerUsuarioPorDni(dni);
            UsuarioResponse usuarioResponse = new UsuarioResponse(
                    usuario.getNombreCompleto(),
                    usuario.getApellidoPaterno(),
                    usuario.getApellidoMaterno(),
                    usuario.getDni(),
                    usuario.getCorreoElectronico(),
                    usuario.getRol(),
                    usuario.getTelefono());
            return ResponseEntity.ok(usuarioResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado con DNI: " + dni);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado al buscar el usuario");
        }
    }
    // Ejemplo de endpoint para obtener un usuario por tipo de rol
    @GetMapping("/buscar/rol/{rol}")
    public ResponseEntity<?> obtenerUsuariosPorRol(@PathVariable String rol) {
        try {
            List<PersonaResponse> usuarios = usuarioService.obtenerUsuariosPorRol(rol);
            if (usuarios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontraron usuarios con el rol: " + rol);
            }
            List<UsuarioResponse> usuariosResponse = usuarios.stream()
                    .map(usuario -> new UsuarioResponse(
                            usuario.getNombreCompleto(),
                            usuario.getApellidoPaterno(),
                            usuario.getApellidoMaterno(),
                            usuario.getDni(),
                            usuario.getCorreoElectronico(),
                            usuario.getRol(),
                            usuario.getTelefono()))
                    .toList();
            return ResponseEntity.ok(usuariosResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado al buscar usuarios por rol");
        }
    }

    // Ejemplo de endpoint para actualizar un usuario
    @PutMapping("/actualizar/{dni}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable String dni, @RequestBody UsuarioResponse usuarioActualizado) {
        try {
            Personas usuario = usuarioService.actualizarUsuario(dni, usuarioActualizado);
            UsuarioResponse usuarioResponse = new UsuarioResponse(
                    usuario.getNombreCompleto(),
                    usuario.getApellidoPaterno(),
                    usuario.getApellidoMaterno(),
                    usuario.getDni(),
                    usuario.getCorreoElectronico(),
                    usuario.getRol(),
                    usuario.getTelefono());
            return ResponseEntity.ok(usuarioResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado con DNI: " + dni);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado al actualizar el usuario");
        }
    }
    // Ejemplo de endpoint para eliminar un usuario por DNI
    @DeleteMapping("/eliminar/{dni}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable String dni) {
        try {
            usuarioService.eliminarUsuario(dni);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado con DNI: " + dni);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado al eliminar el usuario");
        }
    }
}
