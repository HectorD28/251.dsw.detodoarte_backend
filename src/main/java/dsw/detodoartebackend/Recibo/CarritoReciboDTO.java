/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.Recibo;

public record CarritoReciboDTO(
        String tipo,
        String descripcion,
        int cantidad,
        double precioTotal

) {

    public double getSubtotal() {
        return precioTotal;
    }
}
