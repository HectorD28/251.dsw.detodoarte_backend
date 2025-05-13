

package dsw.detodoartebackend.repository;


public interface ArtistaRespository extends JpaRepository<Artista, Long>{
    boolean existsByPersonaId(Long personaId);
}
