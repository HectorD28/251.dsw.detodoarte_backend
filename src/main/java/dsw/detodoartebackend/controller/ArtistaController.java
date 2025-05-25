/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.ArtistaRequest;
import dsw.detodoartebackend.dto.ArtistaResponse;
import dsw.detodoartebackend.service.ArtistaService;
import dsw.detodoartebackend.utils.ErrorResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/artistas")
public class ArtistaController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ArtistaService artistaService;

    @GetMapping
    public ResponseEntity<?> obtenerTodasArtistas() {
        List<ArtistaResponse> listaArtistaResponse = null;
        try {
            listaArtistaResponse = artistaService.obtenerTodasArtistas(); 
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaArtistaResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not found").build());
        return ResponseEntity.ok(listaArtistaResponse); 
    }
    @PostMapping()
    public ResponseEntity<?> registrarArtista(@RequestBody ArtistaRequest artistaRequest) {
        logger.info(">insert " + artistaRequest.toString());
        ArtistaResponse artistaResponse;
        try {
            artistaResponse = artistaService.guardarArtista(artistaRequest);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (artistaResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("No se pudo registrar el artista").build());
        }

        return ResponseEntity.ok(artistaResponse);
    }

}


