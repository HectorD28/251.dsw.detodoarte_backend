
package dsw.detodoartebackend.OrdenDePago;

import dsw.detodoartebackend.ProductoOrden.ProductoOrden;
import dsw.detodoartebackend.ProductoOrden.ProductoOrdenRepository;
import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.service.ObraDeArteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class OrdenPagoService {

    @Autowired
    private OrdenPagoRepository ordenPagoRepository;
    @Autowired
    private ProductoOrdenRepository productoOrdenRepository;

    private final ObraDeArteService productoService;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final Map<ProductoOrden, ScheduledFuture<?>> tareasLiberacion = new ConcurrentHashMap<>();

    public OrdenPagoService(ObraDeArteService productoService) {
        this.productoService = productoService;
    }

    public void programarLiberacionStock(ProductoOrden productoOrden, int minutos) {
        ScheduledFuture<?> tarea = scheduler.schedule(() -> {
            synchronized (productoOrden.getProducto()) {
                if (!productoOrden.isStockDescontado()) {
                    return;
                }
                productoOrden.getProducto().restaurarStock(productoOrden.getCantidad());
                productoOrden.setStockDescontado(false);
                productoService.guardarProducto(productoOrden.getProducto());
            }
        }, minutos, TimeUnit.MINUTES);

        tareasLiberacion.put(productoOrden, tarea);
    }


    public void cancelarLiberacionStock(ProductoOrden productoOrden) {
        ScheduledFuture<?> tarea = tareasLiberacion.remove(productoOrden);
        if (tarea != null) {
            tarea.cancel(false);
        }
    }


    @Transactional
    public OrdenDePago crearOrdenPago(List<ProductoOrden> productosOrden) {
        if (productosOrden == null || productosOrden.isEmpty()) {
            throw new IllegalStateException("No hay productos en la orden de pago :C");
        }

        double total = productosOrden.stream().mapToDouble(ProductoOrden::getSubtotal).sum();
        OrdenDePago orden = new OrdenDePago();
        orden.setTotal(total);

        for (ProductoOrden po : productosOrden) {
            ObraDeArte producto = po.getProducto();

            // verificar si el stock ya fue descontado previamente
            if (!po.isStockDescontado()) {
                if (!producto.reducirStock(po.getCantidad())) {
                    throw new IllegalStateException("No hay suficiente stock para el producto: " + producto.getTitulo());
                }
                po.setStockDescontado(true);
            }

            po.setOrdenDePago(orden);
        }

        orden.setProductos(new ArrayList<>(productosOrden));
        return ordenPagoRepository.save(orden);
    }

    @Transactional
    public void expirarOrden(Long idOrden) {
        OrdenDePago orden = ordenPagoRepository.findByIdOrden(idOrden)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        if (orden.getEstado() != EstadoOrden.PENDIENTE) {
            throw new RuntimeException("Solo se pueden expirar ordenes en estado PENDIENTE");
        }

        // Restaurar el stock de los productos asociados
        for (ProductoOrden productoOrden : orden.getProductos()) {
            
            if (productoOrden.isStockDescontado()) {
                productoOrden.getProducto().restaurarStock(productoOrden.getCantidad());
                productoOrden.setStockDescontado(false);
                productoService.guardarProducto(productoOrden.getProducto());
            }
        }

        // Cambiar estado a EXPIRADO y guardar
        orden.setEstado(EstadoOrden.EXPIRADO);
        ordenPagoRepository.save(orden);
    }



    public void guardarOrden(OrdenDePago orden) {
        ordenPagoRepository.save(orden);
    }

    public List<OrdenDePago> obtenerOrdenes() {
        return ordenPagoRepository.findAll();
    }


    public OrdenDePago obtenerPorId(Long idOrden) {
        return ordenPagoRepository.findByIdOrden(idOrden)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
    }


    public List<OrdenDePago> obtenerOrdenesPendientes() {
        return ordenPagoRepository.findByEstado(EstadoOrden.PENDIENTE);
    }



}

