/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.PersonaRequest;
import dsw.detodoartebackend.dto.PersonaResponse;
import dsw.detodoartebackend.entity.Personas;
import dsw.detodoartebackend.repository.PersonaRepository;
import java.util.List;
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

    public List<PersonaResponse> obtenerTodasPersonas() {
        return PersonaResponse.fromEntities(personaRepository.findAll());
    }

    // Guardar una persona
    public PersonaResponse guardarPersona(PersonaRequest personaRequest) {
    try {
        // Crear la entidad Persona a partir del DTO PersonaRequest
        Personas persona = new Personas(
        
        personaRequest.getPersonaId(),
        personaRequest.getDni(),
        personaRequest.getNombre_completo(),
        personaRequest.getApellido_paterno(),
        personaRequest.getApellido_materno(),
        personaRequest.getDireccion_residencia(),
        personaRequest.getSexo(),
        personaRequest.getTelefono(),
        personaRequest.getCorreo_electronico()
        );
        // Verificación de existencia de DNI y correo electrónico (únicos)
        if (personaRepository.existsByDni(persona.getDni())) {
            throw new RuntimeException("El DNI ya está registrado en el sistema.");
        }
        if (personaRepository.existsByCorreoElectronico(persona.getCorreoElectronico())) {
            throw new RuntimeException("El correo electrónico ya está registrado en el sistema.");
        }

        Personas savedPersona = personaRepository.save(persona);

        // Convertir la entidad guardada Persona en un DTO PersonaResponse y devolverlo
        return PersonaResponse.fromEntity(savedPersona);

    } catch (Exception e) {
        throw new RuntimeException("Error al guardar la persona", e);  // Manejo de errores
    }
}



}
