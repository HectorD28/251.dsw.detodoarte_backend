/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.service.ObraDeArteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/obras")
public class ObraDeArteController {

    @Autowired
    private ObraDeArteService obraDeArteService;

    @PostMapping("/subir")
    public ResponseEntity<String> subirObra(@RequestBody ObraDeArte obraDeArte) {
        obraDeArteService.guardarObra(obraDeArte);
        return ResponseEntity.status(HttpStatus.CREATED).body("Obra registrada exitosamente");
    }

    // Otros endpoints si es necesario
}
