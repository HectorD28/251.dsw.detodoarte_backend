/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dsw.detodoartebackend.service;

import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.repository.ObraDeArteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObraDeArteService {

    @Autowired
    private ObraDeArteRepository obraDeArteRepository;

    public ObraDeArte guardarObra(ObraDeArte obraDeArte) {
        return obraDeArteRepository.save(obraDeArte);
    }
}
