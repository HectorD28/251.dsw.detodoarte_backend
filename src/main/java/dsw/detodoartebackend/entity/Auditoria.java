package dsw.detodoartebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="auditoria")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")
    private Long idAuditoria;

    @Column(name = "tabla_modificada")
    private String tablaModificada;  // Nombre de la tabla que fue modificada

    @Column(name = "id_registro_modificado")
    private Long idRegistroModificado;  // ID del registro modificado

    @Column(name = "campo_modificado")
    private String campoModificado;  // El campo que fue modificado

    @Column(name = "valor_anterior")
    private String valorAnterior;  // Valor antes de la modificación

    @Column(name = "valor_nuevo")
    private String valorNuevo;  // Valor después de la modificación

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;  // Fecha y hora de la modificación

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "persona_id")
    private Personas persona;  // Persona que realizó la modificación
}
