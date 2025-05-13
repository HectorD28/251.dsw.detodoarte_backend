/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.entity.Tecnica;
import dsw.detodoartebackend.service.TecnicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tecnicas")
public class TecnicaController {

    @Autowired
    private TecnicaService tecnicaService;

    @GetMapping("/{id}")
    public Tecnica obtenerTecnica(@PathVariable Long id) {
        return tecnicaService.obtenerTecnicaPorId(id);
    }
}

