/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.PersonaRequest;
import dsw.detodoartebackend.dto.PersonaResponse;
import dsw.detodoartebackend.service.PersonaService;
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
@RequestMapping("/api/personas")
public class PersonaController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<?> obtenerTodasPersonas() {
        List<PersonaResponse> listaPersonaResponse = null;
        try {
            listaPersonaResponse = personaService.obtenerTodasPersonas(); // Llamar al servicio
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaPersonaResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not found").build());
        return ResponseEntity.ok(listaPersonaResponse); 
    }
    @PostMapping()
    public ResponseEntity<?> insertPersona(@RequestBody PersonaRequest personaRequest){
        logger.info(">insert " + personaRequest.toString());
        PersonaResponse personaResponse;
        try{
            personaResponse=personaService.guardarPersona(personaRequest);
            
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(personaResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not insert").build());
        return ResponseEntity.ok(personaResponse);                
    }


}

