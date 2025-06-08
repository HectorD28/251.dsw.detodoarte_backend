/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.controller;

import dsw.detodoartebackend.OrdenDePago.EstadoOrden;
import dsw.detodoartebackend.OrdenDePago.OrdenDePago;
import dsw.detodoartebackend.OrdenDePago.OrdenPagoService;
import dsw.detodoartebackend.ProductoOrden.ProductoOrden;
import dsw.detodoartebackend.ProductoOrden.ProductoOrdenRepository;
import dsw.detodoartebackend.entity.ObraDeArte;
import dsw.detodoartebackend.service.ObraDeArteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin
public class OrdenDePagoController {
    @Autowired
    private OrdenPagoService ordenPagoService;

    @Autowired
    private ObraDeArteService obraDeArteService;

    @Autowired
    private ProductoOrdenRepository productoOrdenRepository;

    private List<ProductoOrden> carrito = new ArrayList<>();

    // endpoint para seleccionar los productos /seleccionar?idProducto=1&cantidad=2
    @PostMapping("/seleccionar")
    public String seleccionarProducto(@RequestParam long idProducto, @RequestParam int cantidad) {
        ObraDeArte producto = obraDeArteService.obtenerPorId(idProducto);
        if (producto == null) {
            return "Producto no encontrado :c";
        }

        for (ProductoOrden pOrden : carrito) {
            if (pOrden.getProducto().getObraId() == idProducto) {
                return "Este producto ya está en el carrito";
            }
        }

        if (!producto.reducirStock(cantidad)) {
            return "No hay suficiente stock disponible para " + producto.getTitulo();
        }

        obraDeArteService.guardarProducto(producto);
        ProductoOrden productoOrden = new ProductoOrden(producto, cantidad);
        productoOrden.setStockDescontado(true);
        carrito.add(productoOrden);

        ordenPagoService.programarLiberacionStock(productoOrden, 15);

        return "Producto reservado: " + producto.getTitulo()+ ", Cantidad: " + cantidad;
    }


    // trae todos los productos seleccionados

    /*
    * Ojo: saldra en este tipo de formato:
    * [
    {
        "id": null,
        "productoNombre": "Alimento para Perros",
        "cantidad": 2,
        "precioUnitario": 50.0,
        "subtotal": 100.0,
        "id_producto": 1
    }
]   aparecera el id null ya que como aun no se confirma la compra, se encuentra en el carrito temporal
* (me avisan si tienen problemas ya que se le puede agregar un id_temporal pero no lo veo necesario)
    * */
    @GetMapping("/productos-seleccionados")
    public List<ProductoOrden> listarProductosSeleccionados() {
        return carrito;
    }

    // endpoint para confirmar orden de pago -> devuelve los productos comprados
    @PostMapping("/confirmar")
    public OrdenDePago confirmarOrden() {
        if (carrito.isEmpty()) {
            return null;
        }

        OrdenDePago orden = ordenPagoService.crearOrdenPago(carrito);

        List<ProductoOrden> productos = new ArrayList<>(orden.getProductos()); // Cargar antes de usar

        for (ProductoOrden productoOrden : productos) {
            //productoOrden.getProducto().confirmarCompra(productoOrden.getCantidad());
            obraDeArteService.guardarProducto(productoOrden.getProducto());
            ordenPagoService.cancelarLiberacionStock(productoOrden);
        }


        orden.setEstado(EstadoOrden.PENDIENTE);
        ordenPagoService.guardarOrden(orden);
        carrito.clear();
        return orden;
    }

    // endpoint para cancelar la orden de pago manualmente -> en carrito estara para que se haga de forma automatica (15 min)
    // se debe de cancelar antes de confirmar sino muere
    @PostMapping("/cancelar")
    public String cancelarOrden() {
        for (ProductoOrden productoOrden : carrito) {
            if (productoOrden.isStockDescontado()) {
                productoOrden.getProducto().restaurarStock(productoOrden.getCantidad());
                productoOrden.setStockDescontado(false);
                obraDeArteService.guardarProducto(productoOrden.getProducto());
            }
            ordenPagoService.cancelarLiberacionStock(productoOrden);
        }
        carrito.clear();
        return "Orden cancelada, stock restaurado";
    }

    @GetMapping("/pendientes")
    public List<OrdenDePago> listarOrdenesPendientes() {
        return ordenPagoService.obtenerOrdenesPendientes();
    }



    @PostMapping("/expirar/{idOrden}")
    public ResponseEntity<String> expirarOrden(@PathVariable Long idOrden) {
        try {
            ordenPagoService.expirarOrden(idOrden);
            return ResponseEntity.ok("La orden de pago " + idOrden + " ha sido marcada como EXPIRADA");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
