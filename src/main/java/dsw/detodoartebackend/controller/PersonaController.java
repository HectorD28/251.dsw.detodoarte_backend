/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.entity.Persona;
import dsw.detodoartebackend.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    public ResponseEntity<List<Persona>> obtenerTodasPersonas() {
        try {
            List<Persona> personas = personaService.obtenerTodasPersonas();
            return new ResponseEntity<>(personas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable Long id) {
        try {
            Persona persona = personaService.obtenerPersonaPorId(id);  // No es necesario usar Optional
            return new ResponseEntity<>(persona, HttpStatus.OK);
        } catch (PersonaService.PersonaNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/dni/{dni}")
    public ResponseEntity<Persona> obtenerPersonaPorDni(@PathVariable String dni) {
        try {
            Persona persona = personaService.obtenerPersonaPorDni(dni);
            return new ResponseEntity<>(persona, HttpStatus.OK);
        } catch (PersonaService.PersonaNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Persona> guardarPersona(@RequestBody Persona persona) {
        try {
            Persona savedPersona = personaService.guardarPersona(persona);
            return new ResponseEntity<>(savedPersona, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
        try {
            personaService.eliminarPersona(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (PersonaService.PersonaNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
