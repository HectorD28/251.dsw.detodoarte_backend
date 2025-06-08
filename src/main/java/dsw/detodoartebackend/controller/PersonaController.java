/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.PersonaRequest;
import dsw.detodoartebackend.dto.PersonaResponse;
import dsw.detodoartebackend.entity.Personas;
import dsw.detodoartebackend.service.PersonaService;
import dsw.detodoartebackend.repository.PersonaRepository;
import dsw.detodoartebackend.utils.ErrorResponse;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.transaction.Transactional;

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
import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PersonaService personaService;

    @Autowired
    private PersonaRepository personasRepository;

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
    @PostMapping("/register")
    public ResponseEntity<?> insertPersona(@RequestBody @Valid PersonaRequest personaRequest
            , UriComponentsBuilder uriBuilder) {
        logger.info(">insert " + personaRequest.toString());

        if (personasRepository.existsByDni(personaRequest.getDni())) {
            return ResponseEntity.badRequest().body(null); // Puedes personalizar el mensaje de error
        }

        PersonaResponse personaResponse;
        URI url =uriBuilder.path("/cliente/{id}").buildAndExpand(personaRequest.getDni()).toUri();
        
        try{
            personaRequest.setEstado(false);
            personaResponse=personaService.guardarPersona(personaRequest);
            

        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(personaResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not insert").build());
        return ResponseEntity.ok(personaResponse);                
    }


    @GetMapping("/details")
    public ResponseEntity<List<PersonaResponse>> listaPersona() {
        List<PersonaResponse> personas = personasRepository.findByEstadoTrue()
                .stream()
                .map(PersonaResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(personas);
    }



    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<PersonaResponse> eliminarCliente (@PathVariable Long id){
    Personas persona = personasRepository.getReferenceById(id);
    persona.desacticarCliente();
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/edit/{id}")
    @Transactional
    public ResponseEntity<PersonaResponse> actualizarCliente (@PathVariable Long id ,@RequestBody @Valid PersonaRequest personaRequest){
    var personas = personasRepository.getReferenceById(id);
       personas.actualizarDatos(personaRequest);
       var personaResponse = new PersonaResponse(personas);
    return ResponseEntity.ok(personaResponse);
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<PersonaResponse> retornarDatosCliente(@PathVariable Long id){

        Personas personas =    personasRepository.getReferenceById(id);
        var personaResponse = new PersonaResponse(personas);
        return ResponseEntity.ok(personaResponse);
    }


}

