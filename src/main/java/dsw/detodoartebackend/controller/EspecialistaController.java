/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.EspecialistaRequest;
import dsw.detodoartebackend.dto.EspecialistaResponse;
import dsw.detodoartebackend.service.EspecialistaService;
import dsw.detodoartebackend.utils.ErrorResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/especialista")
public class EspecialistaController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EspecialistaService especialistaService;

    @GetMapping
    public ResponseEntity<?> obtenerTodasEspecialistas() {
        List<EspecialistaResponse> listaEspecialistaResponse = null;
        try {
            listaEspecialistaResponse = especialistaService.obtenerTodasEspecialistas(); 
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaEspecialistaResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not found").build());
        return ResponseEntity.ok(listaEspecialistaResponse); 
    }
    @PostMapping()
    public ResponseEntity<?> registrarEspecialista(@RequestBody EspecialistaRequest especialistaRequest) {
        logger.info(">insert " + especialistaRequest.toString());
        EspecialistaResponse especialistaResponse;
        try {
            especialistaResponse = especialistaService.guardarEspecialista(especialistaRequest);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (especialistaResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("No se pudo registrar el especialista").build());
        }

        return ResponseEntity.ok(especialistaResponse);
    }
}
