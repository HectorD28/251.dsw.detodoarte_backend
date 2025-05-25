package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.SolicitudExposicionRequest;
import dsw.detodoartebackend.dto.SolicitudExposicionResponse;
import dsw.detodoartebackend.service.SolicitudExposicionService;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solicitudes-exposicion")
public class SolicitudExposicionController {

    @Autowired
    private SolicitudExposicionService solicitudExposicionService;

    @GetMapping
    public ResponseEntity<List<SolicitudExposicionResponse>> obtenerSolicitudes() {
        List<SolicitudExposicionResponse> solicitudes = solicitudExposicionService.obtenerTodas();
        return ResponseEntity.ok(solicitudes);
    }
    
    @PostMapping
    public ResponseEntity<SolicitudExposicionResponse> crearSolicitud(@RequestBody SolicitudExposicionRequest request) {
        SolicitudExposicionResponse response = solicitudExposicionService.crearSolicitud(request);
        return ResponseEntity.ok(response);
    }
}