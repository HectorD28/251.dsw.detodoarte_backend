package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.ObraDeArteRequest;
import dsw.detodoartebackend.dto.ObraDeArteResponse;
import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.entity.Tecnica;
import dsw.detodoartebackend.entity.Artista;
import dsw.detodoartebackend.repository.ObraDeArteRepository;
import dsw.detodoartebackend.repository.TecnicaRepository;
import dsw.detodoartebackend.repository.ArtistaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObraDeArteService {
    @Autowired
    private ObraDeArteRepository obradearteRepository;

    @Autowired
    private TecnicaRepository tecnicaRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    public List<ObraDeArteResponse> obtenerTodasObras() {
        return ObraDeArteResponse.fromEntities(obradearteRepository.findAll());
    }

    public ObraDeArteResponse guardarObra(ObraDeArteRequest obraRequest) {
        // Verificar si el título ya existe
        if (obradearteRepository.existsByTitulo(obraRequest.getTitulo())) {
            throw new RuntimeException("El título ya existe");
        }

        // Buscar la técnica y el artista a partir de los IDs recibidos
        Tecnica tecnica = tecnicaRepository.findById(obraRequest.getId_tecnica())
                .orElseThrow(() -> new RuntimeException("Técnica no encontrada"));

        Artista artista = artistaRepository.findById(obraRequest.getId_artista())
                .orElseThrow(() -> new RuntimeException("Artista no encontrado"));

        // Crear la entidad ObraDeArte
        ObraDeArte obraDeArte = new ObraDeArte();
        obraDeArte.setTitulo(obraRequest.getTitulo());
        obraDeArte.setFechaRealizacion(obraRequest.getFecha_realizacion());
        obraDeArte.setDimensiones(obraRequest.getDimensiones());
        obraDeArte.setTecnica(tecnica); // Asignar la técnica
        obraDeArte.setArtista(artista); // Asignar el artista
        obraDeArte.setPrecio(obraRequest.getPrecio());
        obraDeArte.setCantidadVisualizaciones(obraRequest.getCantidad_Visualizacines());

        // Guardar la obra de arte
        ObraDeArte savedObra = obradearteRepository.save(obraDeArte);

        // Devolver la respuesta
        return ObraDeArteResponse.fromEntity(savedObra);
    }
}
