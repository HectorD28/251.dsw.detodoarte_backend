/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.controller;

import static dsw.detodoartebackend.OrdenDePago.EstadoOrden.PAGADO;
import dsw.detodoartebackend.OrdenDePago.OrdenDePago;
import dsw.detodoartebackend.OrdenDePago.OrdenPagoService;
import dsw.detodoartebackend.ProductoOrden.ProductoOrden;
import dsw.detodoartebackend.Recibo.CarritoReciboDTO;
import dsw.detodoartebackend.Recibo.CarritoReciboService;
import dsw.detodoartebackend.Recibo.Recibo;
import dsw.detodoartebackend.Recibo.ReciboService;
import dsw.detodoartebackend.entity.Personas;
import dsw.detodoartebackend.repository.PersonaRepository;
import dsw.detodoartebackend.service.ObraDeArteService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/carrito")
public class CarritoReciboController {

    private final CarritoReciboService carritoReciboService;
    private final OrdenPagoService ordenDePagoService;
    private final ReciboService reciboService;
    private final PersonaRepository clienteRepository;
    private final ObraDeArteService obraDeArteService;

    public CarritoReciboController(CarritoReciboService carritoReciboService, OrdenPagoService ordenDePagoService, ReciboService reciboService, PersonaRepository clienteRepository, ObraDeArteService obraDeArteService) {
        this.carritoReciboService = carritoReciboService;
        this.ordenDePagoService = ordenDePagoService;
        this.reciboService = reciboService;
        this.clienteRepository = clienteRepository;
        this.obraDeArteService = obraDeArteService;
    }

    @GetMapping("/listar")
    public List<CarritoReciboDTO> listarCarrito() {
        return carritoReciboService.listarCarrito();
    }

    @PostMapping("/agregar/{idOrden}")
    public ResponseEntity<String> agregarOrden(@PathVariable Long idOrden) {
        OrdenDePago orden = ordenDePagoService.obtenerPorId(idOrden);

        if (orden != null) {
            carritoReciboService.agregarOrden(orden);
            return ResponseEntity.ok("Orden de pago agregada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Orden de pago no encontrada :c");
        }
    }


    @DeleteMapping("/vaciar")
    public ResponseEntity<String> vaciarCarrito() {
        if (carritoReciboService.estaVacio()) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("El carrito ya esta vacio");
        }

        carritoReciboService.limpiarCarrito();
        return ResponseEntity.ok("Carrito vaciado ...");
    }



    // para el caso de clientes no registrado usamos:
    // http://localhost:8080/carrito/confirmar/0
    @Transactional
    @PostMapping("/confirmar/{idCliente}")
    public Recibo generarRecibo(@PathVariable(required = false) Long idCliente) {
        Personas cliente = null;

        if (idCliente != null && idCliente > 0) {
            cliente = clienteRepository.findById(idCliente).orElse(null);

            if (cliente != null && !cliente.isEstado()) {
                throw new RuntimeException("Cliente NO encontrado :C");
            }
        }

        List<OrdenDePago> ordenes = carritoReciboService.obtenerCarrito();
        List<CarritoReciboDTO> consultas = carritoReciboService.obtenerConsultas();

        if (ordenes.isEmpty() && consultas.isEmpty()) {
            throw new RuntimeException("Carrito vacio");
        }

        Recibo nuevoRecibo = reciboService.crearRecibo(ordenes, cliente != null ? cliente.getPersona_id() : null);

        for (OrdenDePago orden : ordenes) {
            orden.setEstado(PAGADO);
            ordenDePagoService.guardarOrden(orden);

            for (ProductoOrden productoOrden : orden.getProductos()) {
                if (productoOrden.isStockDescontado()) {
                    continue;
                }
                productoOrden.setStockDescontado(true);
                obraDeArteService.guardarProducto(productoOrden.getProducto());
                ordenDePagoService.cancelarLiberacionStock(productoOrden);
            }
        }

        carritoReciboService.limpiarCarrito();
        return nuevoRecibo;
    }




    @PostMapping("/agregar-consulta/{precioTotal}")
    public int agregarConsulta(@PathVariable double precioTotal) {
        return carritoReciboService.agregarConsulta(precioTotal);
    }

    @DeleteMapping("/quitar-consulta/{index}")
    public void quitarConsulta(@PathVariable int index) {
        carritoReciboService.quitarConsulta(index);
    }



}