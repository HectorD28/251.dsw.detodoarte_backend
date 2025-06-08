
package dsw.detodoartebackend.OrdenDePago;

import dsw.detodoartebackend.ProductoOrden.ProductoOrden;
import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.service.ObraDeArteService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;


public class ExpiracionOrdenWorker {
    @Autowired
    private OrdenPagoRepository ordenRepo;
    
    @Autowired
    private ObraDeArteService obraService;
    
    @Scheduled(fixedRate = 60000)
    @Transactional
    public void verificarOrdenesExpiradas(){
        List<OrdenDePago> ordenesExpiradas =ordenRepo.findByEstadoAndFechaExpiracionBefore(
            EstadoOrden.PENDIENTE, LocalDateTime.now());
        for(OrdenDePago orden : ordenesExpiradas){
            orden.setEstado(EstadoOrden.EXPIRADO);
            for(ProductoOrden productoOrden : orden.getProductos()){
                ObraDeArte obra = productoOrden.getProducto();
                obra.restaurarStock(productoOrden.getCantidad());
                obraService.guardarProducto(obra);
            }
            ordenRepo.save(orden);
        }
    }
    
}
