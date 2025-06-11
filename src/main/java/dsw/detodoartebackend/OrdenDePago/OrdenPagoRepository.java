/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.OrdenDePago;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenPagoRepository extends JpaRepository<OrdenDePago, Long> {
    
    @EntityGraph(attributePaths = {"productos"})
    Optional<OrdenDePago> findByIdOrden(Long idOrden);

    List<OrdenDePago> findByEstadoAndFechaExpiracionBefore(
            EstadoOrden estado, 
            LocalDateTime fechaExpiracion);
    
    List<OrdenDePago> findByEstado(EstadoOrden estado);


}
