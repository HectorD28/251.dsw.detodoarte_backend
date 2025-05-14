package dsw.detodoartebackend.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudObraId implements Serializable {
    private Long solicitud;
    private Long obra;
}
