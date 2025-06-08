/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.Recibo;

import dsw.detodoartebackend.entity.Personas;
import dsw.detodoartebackend.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ValidarPagosService {

    @Autowired
    private PersonaRepository clienteRepository;

    public Long validarDocumento(String documento) {
        // Validar formato: 8 o 9 dígitos
        if (!documento.matches("\\d{8}|\\d{9}")) {
            return 0L;
        }

        Long dni;
        try {
            dni = Long.parseLong(documento);
        } catch (NumberFormatException e) {
            return 0L;
        }

        return clienteRepository.findByDni(documento)
                .map(Personas::getPersona_id)
                .orElse(0L);
    }
}