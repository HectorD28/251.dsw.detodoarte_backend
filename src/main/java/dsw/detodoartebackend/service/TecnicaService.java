/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.service;

import dsw.detodoartebackend.dto.TecnicaRequest;
import dsw.detodoartebackend.dto.TecnicaResponse;
import dsw.detodoartebackend.entity.Tecnica;
import dsw.detodoartebackend.repository.TecnicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TecnicaService {

    private final TecnicaRepository tecnicaRepository;

    public List<TecnicaResponse> obtenerTodasTecnicas() {
        return tecnicaRepository.findAll()
                .stream()
                .map(TecnicaResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public TecnicaResponse guardarTecnica(TecnicaRequest request) {
        Tecnica tecnica = Tecnica.builder()
                .nombreTecnica(request.getNombre_tecnica())
                .build();
        tecnica = tecnicaRepository.save(tecnica);
        return TecnicaResponse.fromEntity(tecnica);
    }

    public TecnicaResponse actualizarTecnica(Long id, TecnicaRequest request) {
        Tecnica tecnica = tecnicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnica no encontrada"));

        tecnica.setNombreTecnica(request.getNombre_tecnica());
        tecnica = tecnicaRepository.save(tecnica);

        return TecnicaResponse.fromEntity(tecnica);
    }

    public void eliminarTecnica(Long id) {
        tecnicaRepository.deleteById(id);
    }
}



