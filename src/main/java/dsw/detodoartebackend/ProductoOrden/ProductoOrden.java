package dsw.detodoartebackend.ProductoOrden;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import dsw.detodoartebackend.OrdenDePago.OrdenDePago;
import dsw.detodoartebackend.entity.ObraDeArte;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "producto_orden")
@Table(name = "producto_orden")
public class ProductoOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden", nullable = false)
    @JsonBackReference
    private OrdenDePago ordenDePago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_obra", nullable = false)
    @JsonIgnore
    private ObraDeArte producto;
    
    @JoinColumn(name = "producto_nombre", nullable = false)
    private String producto_nombre;
    
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    private String tipo = "Producto";
    @Setter
    @Getter
    @Transient
    private boolean stockDescontado = false;

    // solo para devolver el id_producto en JSON correctamente
    @JsonProperty("id_obra")
    public long getIdProducto() {
        return producto.getObraId();
    }

    public ProductoOrden() {}

    public ProductoOrden(ObraDeArte producto, int cantidad) {
        this.producto = producto;
        this.producto_nombre = producto.getTitulo();
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio();
        this.subtotal = this.precioUnitario * this.cantidad;
        this.tipo = "ObraDeArte";
    }
    
}
