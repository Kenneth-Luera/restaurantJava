package com.example.Restaurant.entity;

import java.sql.Types;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

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
    @Column(name = "id_mesa", length = 36)
    @JdbcTypeCode(Types.CHAR)
    private UUID idMesas;

    @Column(name = "numero_mesa", nullable = false)
    private int numeroMesa;

    @Column(name = "numero_asientos", nullable = false)
    private int numeroAsientos;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(Types.VARCHAR)
    @Column(name = "estado_cliente_mesa", nullable = false, length = 30)
    private EstadoClienteMesa estadoClienteMesa;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(Types.VARCHAR)
    @Column(name = "estado_mesa", nullable = false, length = 30)
    private EstadoMesa estadoMesa;
}
