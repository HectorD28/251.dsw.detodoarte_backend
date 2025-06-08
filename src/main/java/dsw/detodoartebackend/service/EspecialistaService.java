package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.EspecialistaRequest;
import dsw.detodoartebackend.dto.EspecialistaResponse;
import dsw.detodoartebackend.entity.Especialista;
import dsw.detodoartebackend.entity.Personas;
import dsw.detodoartebackend.entity.Tecnica;
import dsw.detodoartebackend.repository.EspecialistaRepository;
import dsw.detodoartebackend.repository.PersonaRepository;
import dsw.detodoartebackend.repository.TecnicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialistaService {

    @Autowired
    private EspecialistaRepository especialistaRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private TecnicaRepository tecnicaRepository;

    public List<EspecialistaResponse> getAllEspecialistas() {
        List<Especialista> especialistas = especialistaRepository.findAll();
        return EspecialistaResponse.fromEntities(especialistas);
    }

    public EspecialistaResponse getEspecialistaById(Long id) {
        Especialista especialista = especialistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialista no encontrado con ID " + id));
        return EspecialistaResponse.fromEntity(especialista);
    }

    public EspecialistaResponse createEspecialista(EspecialistaRequest especialistaRequest) {
        Personas persona = personaRepository.findById(especialistaRequest.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID " + especialistaRequest.getPersonaId()));

        Tecnica tecnica = tecnicaRepository.findById(especialistaRequest.getTecnicaId())
                .orElseThrow(() -> new RuntimeException("Técnica no encontrada con ID " + especialistaRequest.getTecnicaId()));

        Especialista especialista = Especialista.builder()
                .persona(persona)
                .tecnica(tecnica)
                .build();

        Especialista savedEspecialista = especialistaRepository.save(especialista);
        return EspecialistaResponse.fromEntity(savedEspecialista);
    }

    public EspecialistaResponse updateEspecialista(Long id, EspecialistaRequest especialistaRequest) {
        Especialista existingEspecialista = especialistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialista no encontrado con ID " + id));

        Personas persona = personaRepository.findById(especialistaRequest.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID " + especialistaRequest.getPersonaId()));

        Tecnica tecnica = tecnicaRepository.findById(especialistaRequest.getTecnicaId())
                .orElseThrow(() -> new RuntimeException("Técnica no encontrada con ID " + especialistaRequest.getTecnicaId()));

        existingEspecialista.setPersona(persona);
        existingEspecialista.setTecnica(tecnica);

        Especialista updatedEspecialista = especialistaRepository.save(existingEspecialista);
        return EspecialistaResponse.fromEntity(updatedEspecialista);
    }

    public void deleteEspecialista(Long id) {
        Especialista especialista = especialistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialista no encontrado con ID " + id));
        especialistaRepository.delete(especialista);
    }
}
