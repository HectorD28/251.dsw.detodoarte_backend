/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.ObraDeArte;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObraDeArteResponse {
    private Integer ObraId;
    private String titulo;
    private LocalDate fechaRealizacion;
    private String dimensiones;
    private Integer tecnicaId;
    private Integer artistaId;
    private Double precio;
    private Integer cantidadVisualizacines;

    public static ObraDeArteResponse fromEntity(ObraDeArte obradearte) {
        return ObraDeArteResponse.builder()
            .ObraId(obradearte.getObraId())
            .titulo(obradearte.getTitulo())
            .fechaRealizacion(obradearte.getFechaRealizacion())
            .dimensiones(obradearte.getDimensiones())
            .tecnicaId(obradearte.getTecnicaId())
            .artistaId(obradearte.getArtistaId())
            .precio(obradearte.getPrecio())
            .cantidadVisualizacines(obradearte.getCantidadVisualizaciones())
            .build();
    }

    public static List<ObraDeArteResponse> fromEntities(List<ObraDeArte> obradeartes) {
        return obradeartes.stream()
                .map(ObraDeArteResponse::fromEntity)
                .collect(Collectors.toList());
    }
}



