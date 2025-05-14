/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.ExposicionRequest;
import dsw.detodoartebackend.dto.ExposicionResponse;
import dsw.detodoartebackend.service.ExposicionService;
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
@RequestMapping("/api/exposicion")
public class ExposicionController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ExposicionService exposicionService;

    @GetMapping("/obtener")
    public ResponseEntity<?> obtenerTodasExposiciones() {
        List<ExposicionResponse> listaExposicionResponse = null;
        try {
            listaExposicionResponse = exposicionService.obtenerTodasExposiciones(); // Llamar al servicio
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaExposicionResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Exposicion not found").build());
        return ResponseEntity.ok(listaExposicionResponse); 
    }
    
    @PostMapping("/subir")
    public ResponseEntity<?> insertObra(@RequestBody ExposicionRequest expoRequest){
        logger.info(">insert " + expoRequest.toString());
        ExposicionResponse obraResponse;
        try{
            obraResponse=exposicionService.guardarExposicion(expoRequest);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(obraResponse==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("Exposicion not insert").build());
        }
        
        return ResponseEntity.ok(obraResponse);                
    }
    
}