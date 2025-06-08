/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.Recibo;

import dsw.detodoartebackend.OrdenDePago.EstadoOrden;
import dsw.detodoartebackend.OrdenDePago.OrdenDePago;
import dsw.detodoartebackend.OrdenDePago.OrdenPagoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPago {
    @Autowired
    private OrdenPagoRepository ordenRepo;

    @Transactional
    public void registrarPago(Long ordenId) {
        OrdenDePago orden = ordenRepo.findById(ordenId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        if (orden.expirada()) {
            throw new RuntimeException("Orden expirada, no se puede pagar");
        }

        orden.setEstado(EstadoOrden.PAGADO);
        ordenRepo.save(orden);
    }
}
