package com.example.Restaurant.entity;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orden")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_orden")
    @JdbcTypeCode(Types.CHAR)
    private UUID idOrden;

    @ManyToOne
    @JoinColumn(name = "id_mesa", nullable = false)
    private Mesas mesa;

    @ManyToMany
    @JoinTable(
        name = "orden_platos",
        joinColumns = @JoinColumn(name = "id_orden"),
        inverseJoinColumns = @JoinColumn(name = "id_plato")
    )
    private List<Platos> platos = new ArrayList<>();

    @Column(name = "precio_total", nullable = false)
    private BigDecimal precioTotal = BigDecimal.ZERO;

    public void setPrecioTotal(BigDecimal precio) {
        this.precioTotal = precio == null ? BigDecimal.ZERO : precio;
    }

}
