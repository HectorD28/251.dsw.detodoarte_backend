/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.detodoartebackend.OrdenDePago;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dsw.detodoartebackend.ProductoOrden.ProductoOrden;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orden_de_pago")

public class OrdenDePago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;

    @Column(name = "total", nullable = false)
    private Double total;

    @Enumerated(EnumType.STRING)
    private EstadoOrden estado = EstadoOrden.PENDIENTE;

    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDateTime fechaExpiracion = fechaCreacion.plusMinutes(15); // 15min de reserva sino se revierte

    @OneToMany(mappedBy = "ordenDePago", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductoOrden> productos = new ArrayList<>();


    public boolean expirada() {
        return LocalDateTime.now().isAfter(fechaExpiracion);
    }
}
