/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.ExposicionRequest;
import dsw.detodoartebackend.dto.ExposicionResponse;
import dsw.detodoartebackend.entity.Exposicion;
import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.repository.ExposicionRepository;
import dsw.detodoartebackend.repository.ObraDeArteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ExposicionService {
    @Autowired
    private ExposicionRepository exposicionRepository;

    @Autowired
    private ObraDeArteRepository obradearteRepository;

    
    public class  ExposicionNotFoundException extends RuntimeException {
        public  ExposicionNotFoundException(String message) {
            super(message);
        }
    }
    

    public List<ExposicionResponse> obtenerTodasExposiciones() {
        return ExposicionResponse.fromEntities(exposicionRepository.findAll());
    }

    public ExposicionResponse guardarExposicion(ExposicionRequest expoRequest) {
        try {

            Exposicion exposicion = new Exposicion();

            exposicion.setNombre(expoRequest.getNombre());
            exposicion.setDescripcion(expoRequest.getDescripcion());
            exposicion.setFecha_inicio(expoRequest.getFecha_inicio());
            exposicion.setFecha_fin(expoRequest.getFecha_fin());
        
            // Guardar la obra de arte
            Exposicion savedObra = exposicionRepository.save(exposicion);

            // Devolver la respuesta
            return ExposicionResponse.fromEntity(savedObra);

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la exposicion ", e);  // Manejo de errores
        }
    }
}


