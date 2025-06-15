package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.TecnicaRequest;
import dsw.detodoartebackend.dto.TecnicaResponse;
import dsw.detodoartebackend.entity.Tecnica;
import dsw.detodoartebackend.repository.TecnicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.repository.ObraDeArteRepository;

import java.util.List;

@Service
public class TecnicaService {

    @Autowired
    private TecnicaRepository tecnicaRepository;
    
    @Autowired
    private ObraDeArteRepository obraDeArteRepository;


    public List<TecnicaResponse> getAllTecnicas() {
        List<Tecnica> tecnicas = tecnicaRepository.findAll();
        return TecnicaResponse.fromEntities(tecnicas);
    }

    public TecnicaResponse getTecnicaById(Long id) {
        Tecnica tecnica = tecnicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnica no encontrada con ID " + id));
        return TecnicaResponse.fromEntity(tecnica);
    }

    public TecnicaResponse createTecnica(TecnicaRequest tecnicaRequest) {
        Tecnica tecnica = Tecnica.builder()
                .nombreTecnica(tecnicaRequest.getNombreTecnica())
                .build();
        Tecnica savedTecnica = tecnicaRepository.save(tecnica);
        return TecnicaResponse.fromEntity(savedTecnica);
    }

    public TecnicaResponse updateTecnica(Long id, TecnicaRequest tecnicaRequest) {
        Tecnica existingTecnica = tecnicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnica no encontrada con ID " + id));

        existingTecnica.setNombreTecnica(tecnicaRequest.getNombreTecnica());
        Tecnica updatedTecnica = tecnicaRepository.save(existingTecnica);
        return TecnicaResponse.fromEntity(updatedTecnica);
    }

    public void deleteTecnica(Long id) {
        Tecnica tecnica = tecnicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnica no encontrada con ID " + id));
        tecnicaRepository.delete(tecnica);
    }
    
    public TecnicaResponse getTecnicaByObraId(Long obraId) {
        // Buscamos la obra por su ID
        ObraDeArte obra = obraDeArteRepository.findById(obraId)
                .orElseThrow(() -> new RuntimeException("Obra no encontrada con ID " + obraId));

        // Obtenemos la técnica asociada a la obra
        Tecnica tecnica = obra.getTecnica(); // Suponiendo que 'getTecnica()' devuelve la técnica asociada

        if (tecnica == null) {
            throw new RuntimeException("No se encontró técnica asociada a la obra con ID " + obraId);
        }

        // Convertimos la técnica a DTO (Data Transfer Object) para enviarla en la respuesta
        return TecnicaResponse.fromEntity(tecnica);
    }
}
