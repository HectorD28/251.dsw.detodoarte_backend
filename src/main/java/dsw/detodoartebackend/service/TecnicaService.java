/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.service;

import dsw.detodoartebackend.entity.Tecnica;
import dsw.detodoartebackend.repository.TecnicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecnicaService {

    @Autowired
    private TecnicaRepository tecnicaRepository;

    public Tecnica obtenerTecnicaPorId(Long id) {
        return tecnicaRepository.findById(id).orElseThrow(() -> new RuntimeException("Técnica no encontrada"));
    }
}

