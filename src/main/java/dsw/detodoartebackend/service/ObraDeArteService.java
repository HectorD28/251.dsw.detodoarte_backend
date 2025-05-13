/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.ObraDeArteRequest;
import dsw.detodoartebackend.dto.ObraDeArteResponse;
import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.repository.ObraDeArteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ObraDeArteService {
    @Autowired
    private ObraDeArteRepository obradearteRepository;
    
    public class ObraDeArteNotFoundException extends RuntimeException {
        public ObraDeArteNotFoundException(String message) {
            super(message);
        }
    }

    public List<ObraDeArteResponse> obtenerTodasObras() {
        return ObraDeArteResponse.fromEntities(obradearteRepository.findAll());
    }

    // Guardar una persona
    public ObraDeArteResponse guardarObra(ObraDeArteRequest obradearteRequest) {
    try {
        // Crear la entidad Persona a partir del DTO PersonaRequest
        ObraDeArte obradearte = new ObraDeArte(
        
        obradearteRequest.getId_obra(),
        obradearteRequest.getTitulo(),
        obradearteRequest.getFecha_realizacion(),
        obradearteRequest.getDimensiones(),
        obradearteRequest.getId_tecnica(),
        obradearteRequest.getId_artista(),
        obradearteRequest.getPrecio(),
        obradearteRequest.getCantidad_Visualizacines()
        );
        // Verificación de existencia de DNI y correo electrónico (únicos)
        if (obradearteRepository.existsByTitulo(obradearte.getTitulo())) {
            throw new RuntimeException("El titulo ya está registrado en el sistema.");
        }
//        if (personaRepository.existsByCorreoElectronico(persona.getCorreoElectronico())) {
//            throw new RuntimeException("El correo electrónico ya está registrado en el sistema.");
//        }

        ObraDeArte savedObra = obradearteRepository.save(obradearte);

        // Convertir la entidad guardada Persona en un DTO PersonaResponse y devolverlo
        return ObraDeArteResponse.fromEntity(savedObra);

    } catch (Exception e) {
        throw new RuntimeException("Error al guardar la obra", e);  // Manejo de errores
    }
}


}

    




