package dsw.detodoartebackend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dsw.detodoartebackend.entity.Personas;
import dsw.detodoartebackend.security.TokenService;
import dsw.detodoartebackend.security.DatosJWTToken;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
@RestController
@RequestMapping("/api")
public class AuthenticactionController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticactionController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datos) {
        System.out.println(datos.username()+"parte1");
        
        try {
            System.out.println(datos.username()+"parte1..5");
            Authentication authToken = new UsernamePasswordAuthenticationToken(
                
                datos.username(),
                datos.contrasena()
            );
            System.out.println(datos.username()+"parte1..7");
            var usuarioAutenticado = authenticationManager.authenticate(authToken);
            System.out.println("Contraseña ingresada: " + datos.contrasena());
            System.out.println("Contraseña en BD: " + usuarioAutenticado.getPrincipal());
            System.out.println(datos.username()+"parte2");
            var JWTtoken = tokenService.generarToken((Personas) usuarioAutenticado.getPrincipal());
            return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno en el servidor");
        }
    }
}
