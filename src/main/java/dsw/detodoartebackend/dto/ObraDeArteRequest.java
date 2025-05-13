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
    private Integer id_obra;
    private String titulo;
    private LocalDate fecha_realizacion;
    private String dimensiones;
    private Long id_tecnica;
    private Long id_artista;
    private Double precio;
    private Integer cantidad_Visualizacines;
}


