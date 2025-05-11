/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import dsw.detodoartebackend.entity.Persona;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    // Aquí puedes agregar consultas personalizadas si es necesario
    // Por ejemplo, buscar por DNI:
    Persona findByDni(String dni);
}
