/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObraDeArteRequest {
    private Long id_obra;
    private String titulo;
    private LocalDate fecha_Realizacion;
    private String dimensiones;
    private String id_tecnica;
    private String id_artistas;
    private Double precio;
    private Integer cantidad_Visualizaciones;
}


