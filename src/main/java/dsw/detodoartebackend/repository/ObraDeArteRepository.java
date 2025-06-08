package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.ObraDeArte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObraDeArteRepository extends JpaRepository<ObraDeArte, Long> {
<<<<<<< HEAD
    List<ObraDeArte> findByArtista_IdArtista(Long idArtista);
=======
     boolean existsByTitulo(String titulo);
     // List<ObraDeArte> findByArtistaId(Long artistaId);
     List<ObraDeArte> findByArtista_IdArtista(Long id_artista);
     
    List<ObraDeArte> findByTituloContainingIgnoreCase(String titulo);

>>>>>>> 54190686d3925c64f2b8a30e6257b3af79f631ca
}
