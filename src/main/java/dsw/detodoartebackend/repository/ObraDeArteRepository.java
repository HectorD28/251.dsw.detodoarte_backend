/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dsw.detodoartebackend.repository;

import dsw.detodoartebackend.entity.ObraDeArte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObraDeArteRepository extends JpaRepository<ObraDeArte, Long> {
    // Métodos personalizados, si los necesitas
}
