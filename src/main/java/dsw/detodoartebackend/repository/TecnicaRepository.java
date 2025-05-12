/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.Tecnica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicaRepository extends JpaRepository<Tecnica, Long> {
    // Métodos personalizados si es necesario
}
