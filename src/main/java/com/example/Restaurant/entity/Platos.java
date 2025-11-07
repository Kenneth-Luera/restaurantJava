package com.example.Restaurant.entity;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Platos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plato")
    private UUID idPlato;

    @Column(name = "nombre_plato",nullable = false, length = 100)
    private String nombrePlato;

    @Column(name = "precio_plato",nullable = false)
    private Double precioPlato;

    @Column(name = "cantidad_platos",nullable = false)
    private int cantidadPlatos;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria",nullable = false)
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado",nullable = false)
    private Estado estado;
}