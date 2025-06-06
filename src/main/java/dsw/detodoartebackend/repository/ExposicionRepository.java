/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.Exposicion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExposicionRepository extends JpaRepository<Exposicion, Long> { 
    // Buscar exposiciones por solicitud
    List<Exposicion> findBySolicitudExposicion_IdSolicitud(Long idSolicitudExposicion);
    
}

