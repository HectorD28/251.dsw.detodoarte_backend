package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.PersonaResponse;
import dsw.detodoartebackend.dto.UsuarioResponse;
import dsw.detodoartebackend.repository.PersonaRepository;
import dsw.detodoartebackend.entity.Personas;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    // Aquí puedes implementar los métodos necesarios para manejar la lógica de negocio relacionada con los usuarios.
    // Por ejemplo, métodos para crear, actualizar, eliminar y obtener usuarios.
    
    @Autowired
    private PersonaService personaService;

    @Autowired
    private PersonaRepository personaRepository;

    // Ejemplo de método para obtener todos los usuarios
    public List<PersonaResponse> listarUsuarios() {
        return personaService.obtenerTodasPersonas();
    }

    // Ejemplo de método para obtener todos los usuarios activos
    public List<PersonaResponse> listarUsuariosActivos() {
        List<PersonaResponse> usuarios = personaService.obtenerTodasPersonas();
        return usuarios.stream()
                .filter(PersonaResponse::isEstado)
                .toList();
    }

    // Ejemplo de método para obtener un usuario por dni
    public PersonaResponse obtenerUsuarioPorDni(String dni) {
        List<PersonaResponse> usuarios = this.listarUsuariosActivos();
        return usuarios.stream()
                .filter(usuario -> usuario.getDni().equals(dni))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con DNI: " + dni));
    }

    // Ejemplo de método para obtener un usuario por tipo de rol
    public List<PersonaResponse> obtenerUsuariosPorRol(String rol) {
        List<PersonaResponse> usuarios = this.listarUsuariosActivos();
        return usuarios.stream()
                .filter(usuario -> usuario.getRol().equalsIgnoreCase(rol))
                .toList();
    }

    // Ejemplo de metodo para actualizar un usuario
    public Personas actualizarUsuario(String dni, UsuarioResponse usuarioActualizado) {
        // Busca la persona por DNI en la base de datos
        Personas personaExistente = personaRepository.findByDni(dni)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con DNI: " + dni));

        // Actualiza los campos necesarios
        personaExistente.setNombreCompleto(usuarioActualizado.getNombreCompleto());
        personaExistente.setApellidoPaterno(usuarioActualizado.getApellidoPaterno());
        personaExistente.setApellidoMaterno(usuarioActualizado.getApellidoMaterno());
        personaExistente.setCorreoElectronico(usuarioActualizado.getCorreoElectronico());
        personaExistente.setTelefono(usuarioActualizado.getTelefono());

        // Guarda los cambios en la base de datos
        return personaRepository.save(personaExistente);
    }

    // Ejemplo de método para eliminar un usuario por DNI
    public void eliminarUsuario(String dni) {
        Personas persona = personaRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con DNI: " + dni));
        persona.setEstado(false); // Cambia el estado a false para "eliminar" lógicamente
        personaRepository.save(persona);
    }
}
