package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.AuditoriaRequest;
import dsw.detodoartebackend.dto.AuditoriaResponse;
import dsw.detodoartebackend.entity.Auditoria;
import dsw.detodoartebackend.entity.Personas;
import dsw.detodoartebackend.repository.AuditoriaRepository;
import dsw.detodoartebackend.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository auditoriaRepository;
    
    @Autowired
    private PersonaRepository personaRepository;

    // Registrar un cambio en la auditoría
    public void registrarAuditoria(AuditoriaRequest request) {
               Personas persona = personaRepository.findById(request.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrado"));
        Auditoria auditoria = Auditoria.builder()
                .tablaModificada(request.getTablaModificada())
                .idRegistroModificado(request.getIdRegistroModificado())
                .campoModificado(request.getCampoModificado())
                .valorAnterior(request.getValorAnterior())
                .valorNuevo(request.getValorNuevo())
                .fechaModificacion(LocalDateTime.now())
                .persona(persona)// El ID de la persona que realizó la modificación
                .build();

        auditoriaRepository.save(auditoria);
    }

    // Obtener todas las auditorías
    public List<AuditoriaResponse> getAllAuditorias() {
        List<Auditoria> auditorias = auditoriaRepository.findAll();
        return AuditoriaResponse.fromEntities(auditorias);
    }

    // Obtener una auditoría por ID
    public AuditoriaResponse getAuditoriaById(Long id) {
        Auditoria auditoria = auditoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auditoría no encontrada con ID " + id));
        return AuditoriaResponse.fromEntity(auditoria);
    }

    // Actualizar una auditoría
    public AuditoriaResponse updateAuditoria(Long id, AuditoriaRequest request) {
        Auditoria existingAuditoria = auditoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auditoría no encontrada con ID " + id));

        existingAuditoria.setTablaModificada(request.getTablaModificada());
        existingAuditoria.setIdRegistroModificado(request.getIdRegistroModificado());
        existingAuditoria.setCampoModificado(request.getCampoModificado());
        existingAuditoria.setValorAnterior(request.getValorAnterior());
        existingAuditoria.setValorNuevo(request.getValorNuevo());

        Auditoria updatedAuditoria = auditoriaRepository.save(existingAuditoria);
        return AuditoriaResponse.fromEntity(updatedAuditoria);
    }

    // Eliminar una auditoría
    public void deleteAuditoria(Long id) {
        Auditoria auditoria = auditoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auditoría no encontrada con ID " + id));
        auditoriaRepository.delete(auditoria);
    }
}
