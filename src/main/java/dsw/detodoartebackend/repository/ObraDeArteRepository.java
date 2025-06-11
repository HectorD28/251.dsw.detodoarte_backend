package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.ObraDeArte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObraDeArteRepository extends JpaRepository<ObraDeArte, Long> {
     boolean existsByTitulo(String titulo);// Método para verificar si ya existe una obra con ese título

     // List<ObraDeArte> findByArtistaId(Long artistaId);
     List<ObraDeArte> findByArtista_IdArtista(Long id_artista);
     
    List<ObraDeArte> findByTituloContainingIgnoreCase(String titulo);

}
