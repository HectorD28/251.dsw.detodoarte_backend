package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.ArtistaRequest;
import dsw.detodoartebackend.dto.ArtistaResponse;
import dsw.detodoartebackend.entity.Artista;
import dsw.detodoartebackend.entity.Persona;
import dsw.detodoartebackend.repository.ArtistaRepository;
import dsw.detodoartebackend.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private PersonaRepository personaRepository;

    public List<ArtistaResponse> getAllArtistas() {
        List<Artista> artistas = artistaRepository.findAll();
        return ArtistaResponse.fromEntities(artistas);
    }

    public ArtistaResponse getArtistaById(Long id) {
        Artista artista = artistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artista no encontrado con ID " + id));
        return ArtistaResponse.fromEntity(artista);
    }

    public ArtistaResponse createArtista(ArtistaRequest artistaRequest) {
        Persona persona = personaRepository.findById(artistaRequest.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID " + artistaRequest.getPersonaId()));

        Artista artista = Artista.builder()
                .persona(persona)
                .build();

        Artista savedArtista = artistaRepository.save(artista);
        return ArtistaResponse.fromEntity(savedArtista);
    }

    public ArtistaResponse updateArtista(Long id, ArtistaRequest artistaRequest) {
        Artista existingArtista = artistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artista no encontrado con ID " + id));

        Persona persona = personaRepository.findById(artistaRequest.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID " + artistaRequest.getPersonaId()));

        existingArtista.setPersona(persona);
        Artista updatedArtista = artistaRepository.save(existingArtista);
        return ArtistaResponse.fromEntity(updatedArtista);
    }

    public void deleteArtista(Long id) {
        Artista artista = artistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artista no encontrado con ID " + id));
        artistaRepository.delete(artista);
    }
}
