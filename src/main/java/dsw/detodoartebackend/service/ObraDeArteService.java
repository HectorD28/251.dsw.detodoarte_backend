package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.ObraDeArteRequest;
import dsw.detodoartebackend.dto.ObraDeArteResponse;
import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.entity.Tecnica;
import dsw.detodoartebackend.entity.Artista;
import dsw.detodoartebackend.repository.ObraDeArteRepository;
import dsw.detodoartebackend.repository.TecnicaRepository;
import dsw.detodoartebackend.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObraDeArteService {

    @Autowired
    private ObraDeArteRepository obraDeArteRepository;

    @Autowired
    private TecnicaRepository tecnicaRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    public List<ObraDeArteResponse> obtenerTodasObras() {
        List<ObraDeArte> obras = obraDeArteRepository.findAll();
        return ObraDeArteResponse.fromEntities(obras);
    }

    public ObraDeArteResponse obtenerObraPorId(Long id) {
        ObraDeArte obra = obraDeArteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obra no encontrada con ID " + id));
        return ObraDeArteResponse.fromEntity(obra);
    }

    public List<ObraDeArteResponse> obtenerObrasPorArtista(Long idArtista) {
        List<ObraDeArte> obras = obraDeArteRepository.findByArtista_IdArtista(idArtista);
        return ObraDeArteResponse.fromEntities(obras);
    }
    
    public boolean verificarTituloUnico(String titulo) {
        return !obraDeArteRepository.existsByTitulo(titulo);
    }

    public ObraDeArteResponse guardarObra(ObraDeArteRequest request) {
        
        if (!verificarTituloUnico(request.getTitulo())) {
            throw new RuntimeException("El título de la obra ya existe.");
        }
        
        Tecnica tecnica = tecnicaRepository.findById(request.getId_tecnica())
                .orElseThrow(() -> new RuntimeException("Técnica no encontrada"));
        Artista artista = artistaRepository.findById(request.getId_artista())
                .orElseThrow(() -> new RuntimeException("Artista no encontrado"));

        ObraDeArte obra = ObraDeArte.builder()
                .titulo(request.getTitulo())
                .fechaRealizacion(request.getFecha_realizacion())
                .dimensiones(request.getDimensiones())
                .tecnica(tecnica)
                .artista(artista)
                .cantidadVisualizaciones(0)
                .estado_publicacion("PENDIENTE")
                .stock(request.getStock())
                .rutaImagen(request.getRuta_imagen())
                .build();

        ObraDeArte saved = obraDeArteRepository.save(obra);
        return ObraDeArteResponse.fromEntity(saved);
    }

    public ObraDeArteResponse actualizarObra(Long id, ObraDeArteRequest request) {
        ObraDeArte obraExistente = obraDeArteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obra no encontrada con ID " + id));

            // Verificar si el título ha cambiado y si el nuevo título ya existe
        if (!obraExistente.getTitulo().equals(request.getTitulo()) && !verificarTituloUnico(request.getTitulo())) {
            throw new RuntimeException("El título de la obra ya existe.");
        }
    
        Tecnica tecnica = tecnicaRepository.findById(request.getId_tecnica())
                .orElseThrow(() -> new RuntimeException("Técnica no encontrada"));
        Artista artista = artistaRepository.findById(request.getId_artista())
                .orElseThrow(() -> new RuntimeException("Artista no encontrado"));

        obraExistente.setTitulo(request.getTitulo());
        obraExistente.setFechaRealizacion(request.getFecha_realizacion());
        obraExistente.setDimensiones(request.getDimensiones());
        obraExistente.setTecnica(tecnica);
        obraExistente.setArtista(artista);
        obraExistente.setPrecio(request.getPrecio());

        ObraDeArte updated = obraDeArteRepository.save(obraExistente);
        return ObraDeArteResponse.fromEntity(updated);
    }

    public void eliminarObra(Long id) {
        ObraDeArte obra = obraDeArteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obra no encontrada con ID " + id));
        obraDeArteRepository.delete(obra);
    }
    
    
    
    
        @Autowired
    private ObraDeArteRepository productoRepository;

   

    public List<ObraDeArte> listarTodos(){
        return productoRepository.findAll();
    }



    public ObraDeArte obtenerPorId(long ObraId) {
        return productoRepository.findById(ObraId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado : c"));
    }
    
    public ObraDeArte guardarProducto(ObraDeArte producto) {
        return productoRepository.save(producto);
    }
    
    
    public List<ObraDeArte> buscarPorNombre(String nombre) {
        return productoRepository.findByTituloContainingIgnoreCase(nombre);
    }

    
    
    
}
