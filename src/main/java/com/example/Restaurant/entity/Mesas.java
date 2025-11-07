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
public class Mesas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_mesa")
    private UUID idMesas;

    @Column(name = "numero_mesa", nullable = false)
    private int numeroMesa;

    @Column(name = "numero_asientos", nullable =  false)
    private int numeroAsientos;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_facturacion", nullable = false)
    private EstadoFacturacion estadoFacturacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_clienteMesa", nullable = false)
    private EstadoClienteMesa estadoClienteMesa;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_mesa",nullable = false)
    private EstadoMesa estadoMesa;

}
