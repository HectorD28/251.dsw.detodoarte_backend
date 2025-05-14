/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.ArtistaRequest;
import dsw.detodoartebackend.dto.ArtistaResponse;
import dsw.detodoartebackend.dto.PersonaRequest;
import dsw.detodoartebackend.dto.PersonaResponse;
import dsw.detodoartebackend.entity.Artista;
import dsw.detodoartebackend.entity.Personas;
import dsw.detodoartebackend.repository.ArtistaRepository;
import dsw.detodoartebackend.repository.PersonaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;
    
    @Autowired
    private PersonaRepository personaRepository;
    
    public class ArtistaNotFoundException extends RuntimeException {
        public ArtistaNotFoundException(String message) {
            super(message);
        }
    }

    public List<ArtistaResponse> obtenerTodasArtistas() {
        return ArtistaResponse.fromEntities(artistaRepository.findAll());
    }


    public ArtistaResponse guardarArtista(ArtistaRequest artistaRequest) {
        

        try {
            System.out.println("\nPRIMER PASO");
            long persona_id = artistaRequest.getPersona_id();
            //System.out.println("\n"+persona_id);

            Personas persona= personaRepository.findById(persona_id).get();
            if(persona==null){
                System.out.println("\nPersona con ID " + persona_id + " NO ENCONTRADO");
                return new ArtistaResponse();
            }

            Artista artista= new Artista(
                persona
            );
            System.out.println("\nPersona con ID " + artista.getId_artista() + " NO REGISTRADO EN SISTEMA");
            //if (artistaRepository.existsById(artista.getId_artista())) {
              //  throw new RuntimeException("El artista ya está registrado en el sistema.");
            //}
            System.out.println("\nPersona con ID " + persona_id + " NO REGISTRADO EN SISTEMA");
            artista=artistaRepository.save(artista);
            System.out.println("\nPersona con ID " + persona_id + " GUARDADO");

            return ArtistaResponse.fromEntity(artista);

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el artista", e);  // Manejo de errores
        }
    }
}

