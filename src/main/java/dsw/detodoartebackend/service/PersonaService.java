/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.service;

import dsw.detodoartebackend.entity.Persona;
import dsw.detodoartebackend.repository.PersonaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;
    
    public class PersonaNotFoundException extends RuntimeException {
        public PersonaNotFoundException(String message) {
            super(message);
        }
    }

    public List<Persona> obtenerTodasPersonas() {
        try {
            return personaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener todas las personas", e);
        }
    }

    public Persona obtenerPersonaPorId(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new PersonaNotFoundException("Persona no encontrada con id: " + id));
    }

    public Persona obtenerPersonaPorDni(String dni) {
        Persona persona = personaRepository.findByDni(dni);
        if (persona == null) {
            throw new PersonaNotFoundException("Persona no encontrada con DNI: " + dni);
        }
        return persona;
    }

    
    //CD PARTE DEL CRUD
    public Persona guardarPersona(Persona persona) {
        try {
            return personaRepository.save(persona);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la persona", e);
        }
    }

    public void eliminarPersona(Long id) {
        try {
            if (!personaRepository.existsById(id)) {
                throw new PersonaNotFoundException("Persona no encontrada con id: " + id);
            }
            personaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la persona", e);
        }
    }
}
