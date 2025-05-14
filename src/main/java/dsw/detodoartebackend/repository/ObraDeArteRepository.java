/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.ObraDeArte;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraDeArteRepository extends JpaRepository<ObraDeArte, Integer> {
     boolean existsByTitulo(String titulo);
     // List<ObraDeArte> findByArtistaId(Integer artistaId);
}

