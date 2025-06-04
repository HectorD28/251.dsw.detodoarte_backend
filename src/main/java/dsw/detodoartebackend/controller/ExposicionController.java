/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dsw.detodoartebackend.dto.ExposicionRequest;
import dsw.detodoartebackend.dto.ExposicionResponse;
import dsw.detodoartebackend.entity.Exposicion;
import dsw.detodoartebackend.entity.SolicitudExposicion;
import dsw.detodoartebackend.repository.ExposicionRepository;
import dsw.detodoartebackend.repository.SolicitudExposicionRepository;
import dsw.detodoartebackend.service.ExposicionService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/exposiciones")
public class ExposicionController {
    private final ExposicionRepository exposicionRepository;
    private final SolicitudExposicionRepository solicitudExposicionRepository;

    @Autowired
    private ExposicionService exposicionService;

    public ExposicionController(ExposicionRepository exposicionRepository,
                                SolicitudExposicionRepository solicitudExposicionRepository) {
        this.exposicionRepository = exposicionRepository;
        this.solicitudExposicionRepository = solicitudExposicionRepository;
    }

    @PostMapping("/programar")
    public ResponseEntity<ExposicionResponse> programarExposicion(@RequestBody ExposicionRequest request) {
        Exposicion exposicion = exposicionService.programarExposicion(request);
        return ResponseEntity.ok(ExposicionResponse.fromEntity(exposicion));
    }

    @GetMapping("/todas")
    public ResponseEntity<List<ExposicionResponse>> listarTodas() {
        List<Exposicion> exposiciones = exposicionRepository.findAll();
        return ResponseEntity.ok(ExposicionResponse.fromEntities(exposiciones));
    }
}