
package dsw.detodoartebackend.ReciboOrden;

import dsw.detodoartebackend.OrdenDePago.OrdenDePago;
import dsw.detodoartebackend.Recibo.Recibo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "recibo_orden")
@Table(name = "recibo_orden")
public class ReciboOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id_recibo_orden;

    @ManyToOne
    @JoinColumn(name = "id_recibo", nullable = false)
    private Recibo recibo;

    @ManyToOne
    @JoinColumn(name = "id_orden", nullable = false)
    private OrdenDePago ordenDePago;
}
