package com.example.Restaurant.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orden_plato")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenPlato {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_orden", nullable = false)
    @JsonBackReference
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "id_plato", nullable = false)
    private Platos plato;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private BigDecimal precioUnitario;

    @Column(nullable = false)
    private BigDecimal subtotal;
}
