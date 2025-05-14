package dsw.detodoartebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dsw.detodoartebackend.entity.SolicitudObra;
import dsw.detodoartebackend.entity.SolicitudObraId;

public interface SolicitudObraRepository extends JpaRepository<SolicitudObra, SolicitudObraId> {

}
