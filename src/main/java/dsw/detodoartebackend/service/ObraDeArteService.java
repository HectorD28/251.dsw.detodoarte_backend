package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.ObraDeArteRequest;
import dsw.detodoartebackend.dto.ObraDeArteResponse;
import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.entity.Tecnica;
import dsw.detodoartebackend.entity.Artista;
import dsw.detodoartebackend.repository.ObraDeArteRepository;
import dsw.detodoartebackend.repository.TecnicaRepository;
import dsw.detodoartebackend.repository.ArtistaRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ObraDeArteService {

    @Autowired
    private ObraDeArteRepository obraRepository;

    @Autowired
    private TecnicaRepository tecnicaRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    public List<ObraDeArteResponse> obtenerTodasObras() {
        return ObraDeArteResponse.fromEntities(obraRepository.findAll());
    }

    public ObraDeArteResponse obtenerObraPorId(Long id) {
        ObraDeArte obra = obraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obra no encontrada con ID " + id));
        return ObraDeArteResponse.fromEntity(obra);
    }

    public List<ObraDeArteResponse> obtenerObrasPorArtista(Long idArtista) {
        return ObraDeArteResponse.fromEntities(obraRepository.findByArtista_IdArtista(idArtista));
    }

    public ObraDeArteResponse guardarObra(ObraDeArteRequest request) {
        if (obraRepository.existsByTitulo(request.getTitulo())) {
            throw new RuntimeException("El título ya existe");
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
                .precio(request.getPrecio())
                .cantidadVisualizaciones(0) // Inicializamos en cero
                .build();

        ObraDeArte saved = obraRepository.save(obra);
        return ObraDeArteResponse.fromEntity(saved);
    }

    public ObraDeArteResponse actualizarObra(Long id, ObraDeArteRequest request) {
        ObraDeArte obraExistente = obraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obra no encontrada con ID " + id));

        // Si el título cambia, verificamos duplicados
        if (!obraExistente.getTitulo().equals(request.getTitulo()) &&
                obraRepository.existsByTitulo(request.getTitulo())) {
            throw new RuntimeException("El título ya existe");
        }

        Tecnica tecnica = tecnicaRepository.findById(request.getId_tecnica())
                .orElseThrow(() -> new RuntimeException("Técnica no encontrada"));
        Artista artista = artistaRepository.findById(request.getId_artista())
                .orElseThrow(() -> new RuntimeException("Artista no encontrado"));

        // Actualizamos campos
        obraExistente.setTitulo(request.getTitulo());
        obraExistente.setFechaRealizacion(request.getFecha_realizacion());
        obraExistente.setDimensiones(request.getDimensiones());
        obraExistente.setTecnica(tecnica);
        obraExistente.setArtista(artista);
        obraExistente.setPrecio(request.getPrecio());
        // No permitimos modificar cantidad de visualizaciones desde aquí

        ObraDeArte updated = obraRepository.save(obraExistente);
        return ObraDeArteResponse.fromEntity(updated);
    }

    public void eliminarObra(Long id) {
        ObraDeArte obra = obraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obra no encontrada con ID " + id));
        
        // Aquí debes agregar lógica para validar si la obra está en exposición o vendida
        // Ejemplo simplificado (supongamos tienes un campo estado, por ahora omito):
        /*
        if (obra.getEstado().equals("En exposición") || obra.getEstado().equals("Vendida")) {
            throw new RuntimeException("No se puede eliminar obra en exposición o vendida");
        }
        */

        obraRepository.delete(obra);
    }

    // Método para incrementar visualizaciones (puedes usarlo cuando un usuario vea la obra)
    public void incrementarVisualizaciones(Long id) {
        ObraDeArte obra = obraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obra no encontrada con ID " + id));
        obra.setCantidadVisualizaciones(obra.getCantidadVisualizaciones() + 1);
        obraRepository.save(obra);
    }
    
        private final String carpetaUploads = "uploads/obras/";

    // Método para guardar la imagen en disco y devolver el nombre generado
    public String guardarImagen(MultipartFile archivo) throws IOException {
        if (archivo.isEmpty()) {
            throw new IOException("Archivo vacío");
        }
        File carpeta = new File(carpetaUploads);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        String nombreArchivo = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
        Path rutaArchivo = Paths.get(carpetaUploads, nombreArchivo);
        Files.write(rutaArchivo, archivo.getBytes());
        return nombreArchivo;
    }

    // Método para actualizar solo la ruta de imagen de la obra
    public ObraDeArteResponse actualizarRutaImagen(Long id, MultipartFile archivo) throws IOException {
        ObraDeArte obra = obraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obra no encontrada con ID " + id));

        String nombreArchivo = guardarImagen(archivo);
        obra.setRutaImagen(nombreArchivo);
        obraRepository.save(obra);

        return ObraDeArteResponse.fromEntity(obra);
    }
}
