package com.example.Restaurant.entity;


import java.math.BigDecimal;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private UUID idOrden;

    @ManyToOne
    @JoinColumn(name = "numero_mesa", referencedColumnName = "numero_mesa")
    private Mesas numeroMesa; 
    
    @ManyToOne
    @JoinColumn(name = "platos", referencedColumnName = "id_plato")
    private Platos platos; 

    @Column(name = "precio_total", nullable = false)
    private BigDecimal precioTotal;
}
