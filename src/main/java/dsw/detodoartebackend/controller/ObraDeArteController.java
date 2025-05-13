/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.dto.ObraDeArteRequest;
import dsw.detodoartebackend.dto.ObraDeArteResponse;
import dsw.detodoartebackend.service.ObraDeArteService;
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
@RequestMapping("/api/obras")
public class ObraDeArteController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ObraDeArteService obradearteService;

    @GetMapping("/obtener")
    public ResponseEntity<?> obtenerTodasObras() {
        List<ObraDeArteResponse> listaObrasResponse = null;
        try {
            listaObrasResponse = obradearteService.obtenerTodasObras(); // Llamar al servicio
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaObrasResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Obra not found").build());
        return ResponseEntity.ok(listaObrasResponse); 
    }
    @PostMapping("/subir")
    public ResponseEntity<?> insertObra(@RequestBody ObraDeArteRequest obraRequest){
        logger.info(">insert " + obraRequest.toString());
        ObraDeArteResponse obraResponse;
        try{
            if (obraRequest.getId_obra() != null) {
                 throw new IllegalArgumentException("El ID no debe ser proporcionado al crear una nueva obra.");
            }   
            obraResponse=obradearteService.guardarObra(obraRequest);
            
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(obraResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Obra not insert").build());
        return ResponseEntity.ok(obraResponse);                
    }

}

