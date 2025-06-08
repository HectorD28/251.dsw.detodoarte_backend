package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.ObraDeArte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObraDeArteRepository extends JpaRepository<ObraDeArte, Long> {
    List<ObraDeArte> findByArtista_IdArtista(Long idArtista);
}
