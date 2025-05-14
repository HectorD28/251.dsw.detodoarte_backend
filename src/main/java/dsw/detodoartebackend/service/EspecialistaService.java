
package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.EspecialistaRequest;
import dsw.detodoartebackend.dto.EspecialistaResponse;
import dsw.detodoartebackend.entity.Especialista;
import dsw.detodoartebackend.entity.Personas;
import dsw.detodoartebackend.entity.Tecnica;
import dsw.detodoartebackend.repository.EspecialistaRepository;
import dsw.detodoartebackend.repository.PersonaRepository;
import dsw.detodoartebackend.repository.TecnicaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspecialistaService {
    @Autowired
    private EspecialistaRepository especialistaRepository;
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @Autowired
    private TecnicaRepository tecnicaRepository;
    
    public class EspecialistaNotFoundException extends RuntimeException {
        public EspecialistaNotFoundException(String message) {
            super(message);
        }
    }

    public List<EspecialistaResponse> obtenerTodasEspecialistas() {
        return EspecialistaResponse.fromEntities(especialistaRepository.findAll());
    }


    public EspecialistaResponse guardarEspecialista(EspecialistaRequest especialistaRequest) {
        

        try {
            System.out.println("\nPRIMER PASO");
            
            long persona_id = especialistaRequest.getPersona_id();
            Personas persona= personaRepository.findById(persona_id).get();
            if(persona==null){
                System.out.println("\nPersona con ID " + persona_id + " NO ENCONTRADO");
                return new EspecialistaResponse();
            }
            
            long id_tecnica = especialistaRequest.getId_tecnica();
            Tecnica tecnica= tecnicaRepository.findById(id_tecnica).get();
            if(tecnica==null){
                System.out.println("\nTecnica con ID " + id_tecnica + " NO ENCONTRADO");
                return new EspecialistaResponse();
            }
            
            

            Especialista especialista= new Especialista(
                persona,
                tecnica
            );

            if (especialistaRepository.existsById(persona.getPersona_id())) {
                throw new RuntimeException("El especialista ya está registrado en el sistema a nombre de "+persona.getNombreCompleto());
            }
            especialista=especialistaRepository.save(especialista);
            //System.out.println("\nPersona con ID " + persona_id + " GUARDADO");

            return EspecialistaResponse.fromEntity(especialista);

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el especialista", e);  // Manejo de errores
        }
    }
}
