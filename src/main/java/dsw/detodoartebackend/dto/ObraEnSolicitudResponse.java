package dsw.detodoartebackend.dto;

import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.entity.SolicitudExposicion;
import lombok.Data;

@Data
public class ObraEnSolicitudResponse {
    private SolicitudExposicion solicitud;
    private ObraDeArte obra;
    
    //private Especialista especialista;
    
    private String estadoObra = "PENDIENTE";
}
