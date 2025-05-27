/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.TecnicaRequest;
import dsw.detodoartebackend.dto.TecnicaResponse;
import dsw.detodoartebackend.service.TecnicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tecnicas")
@RequiredArgsConstructor
public class TecnicaController {

    private final TecnicaService tecnicaService;

    @GetMapping
    public ResponseEntity<List<TecnicaResponse>> obtenerTodas() {
        return ResponseEntity.ok(tecnicaService.obtenerTodasTecnicas());
    }

    @PostMapping
    public ResponseEntity<TecnicaResponse> crear(@RequestBody TecnicaRequest request) {
        return ResponseEntity.ok(tecnicaService.guardarTecnica(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicaResponse> actualizar(@PathVariable Long id, @RequestBody TecnicaRequest request) {
        return ResponseEntity.ok(tecnicaService.actualizarTecnica(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tecnicaService.eliminarTecnica(id);
        return ResponseEntity.noContent().build();
    }
}



