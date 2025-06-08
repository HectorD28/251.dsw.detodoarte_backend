package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.AdministradorRequest;
import dsw.detodoartebackend.dto.AdministradorResponse;
import dsw.detodoartebackend.entity.Administrador;
import dsw.detodoartebackend.entity.Personas;
import dsw.detodoartebackend.repository.AdministradorRepository;
import dsw.detodoartebackend.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private PersonaRepository personaRepository;

    public List<AdministradorResponse> getAllAdministradores() {
        List<Administrador> administradores = administradorRepository.findAll();
        return AdministradorResponse.fromEntities(administradores);
    }

    public AdministradorResponse getAdministradorById(Long id) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID " + id));
        return AdministradorResponse.fromEntity(administrador);
    }

    public AdministradorResponse createAdministrador(AdministradorRequest request) {
        Personas persona = personaRepository.findById(request.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        Administrador administrador = Administrador.builder()
                .persona(persona)
                .password(request.getPassword())
                .tipoUsuario(request.getTipoUsuario())
                .build();

        Administrador savedAdministrador = administradorRepository.save(administrador);
        return AdministradorResponse.fromEntity(savedAdministrador);
    }

    public AdministradorResponse updateAdministrador(Long id, AdministradorRequest request) {
        Administrador existingAdministrador = administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID " + id));

        Personas persona = personaRepository.findById(request.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        existingAdministrador.setPersona(persona);
        existingAdministrador.setPassword(request.getPassword());
        existingAdministrador.setTipoUsuario(request.getTipoUsuario());

        Administrador updatedAdministrador = administradorRepository.save(existingAdministrador);
        return AdministradorResponse.fromEntity(updatedAdministrador);
    }

    public void deleteAdministrador(Long id) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID " + id));
        administradorRepository.delete(administrador);
    }
}
