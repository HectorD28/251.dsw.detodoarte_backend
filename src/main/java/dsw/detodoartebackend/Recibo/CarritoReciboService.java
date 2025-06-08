/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.Recibo;

import dsw.detodoartebackend.OrdenDePago.OrdenDePago;
import dsw.detodoartebackend.ProductoOrden.ProductoOrden;
import dsw.detodoartebackend.ProductoOrden.ProductoOrdenRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoReciboService {

    private final ProductoOrdenRepository productoOrdenRepository;
    private final List<OrdenDePago> carrito = new ArrayList<>();
    private final List<CarritoReciboDTO> consultasCarrito = new ArrayList<>();

    public CarritoReciboService(ProductoOrdenRepository productoOrdenRepository) {
        this.productoOrdenRepository = productoOrdenRepository;
    }

    public List<CarritoReciboDTO> listarCarrito() {
        List<CarritoReciboDTO> lista = new ArrayList<>();

        // devuelve todos los productos en las ordenes del carrito
        for (OrdenDePago orden : carrito) {
            List<ProductoOrden> productosOrdenados = productoOrdenRepository.findByOrdenDePago_IdOrden(orden.getIdOrden());
            for (ProductoOrden productoOrden : productosOrdenados) {
                lista.add(new CarritoReciboDTO(
                        productoOrden.getTipo(),
                        productoOrden.getProductoNombre(),
                        productoOrden.getCantidad(),
                        productoOrden.getSubtotal()
                ));
            }
        }


        lista.addAll(consultasCarrito);

        return lista;
    }

    public void agregarOrden(OrdenDePago orden) {
        if (!carrito.contains(orden)) {
            carrito.add(orden);
        }
    }

    public void quitarOrden(Long idOrden) {
        carrito.removeIf(o -> o.getIdOrden().equals(idOrden));
    }

    public int agregarConsulta(double precioTotal) {
        CarritoReciboDTO consulta = new CarritoReciboDTO("Servicio", "Consulta", 1, precioTotal);
        consultasCarrito.add(consulta);
        return consultasCarrito.size() - 1; // devuelve el indice de la consulta agregada para saber cual eliminar
    }


    public void quitarConsulta(int index) {
        if (index >= 0 && index < consultasCarrito.size()) {
            consultasCarrito.remove(index);
        }
    }


    public void limpiarCarrito() {
        carrito.clear();
        consultasCarrito.clear();
    }

    public boolean estaVacio() {
        return carrito.isEmpty() && consultasCarrito.isEmpty();
    }


    public List<OrdenDePago> obtenerCarrito() {
        return carrito;
    }

    public List<CarritoReciboDTO> obtenerConsultas() {
        return consultasCarrito;
    }

}
