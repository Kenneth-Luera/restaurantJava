package com.example.Restaurant.entity;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_orden", length = 36)
    @JdbcTypeCode(Types.CHAR)
    private UUID idOrden;

    @ManyToOne
    @JoinColumn(name = "id_mesa", nullable = false)
    private Mesas mesa;

    @Column(name = "precio_total", nullable = false)
    private BigDecimal precioTotal = BigDecimal.ZERO;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrdenPlato> items = new ArrayList<>();
}
