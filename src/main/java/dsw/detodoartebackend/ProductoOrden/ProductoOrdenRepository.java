/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.ProductoOrden;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoOrdenRepository extends JpaRepository<ProductoOrden, Long>{
    List<ProductoOrden> findByOrdenDePago_IdOrden(Long idOrden);
}
