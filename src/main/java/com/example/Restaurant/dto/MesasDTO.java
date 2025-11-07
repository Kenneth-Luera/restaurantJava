package com.example.Restaurant.dto;

import com.example.Restaurant.entity.EstadoClienteMesa;
import com.example.Restaurant.entity.EstadoFacturacion;
import com.example.Restaurant.entity.EstadoMesa;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MesasDTO {
    private UUID idMesas;

    @NotNull(message = "el numero de la mesa no puede estar vacio")
    @Min(value = 0, message = "el numero debe ser mayor o igual a 0")
    private int numeroMesa;

    @NotNull(message = "el numero de asientos no puede ser 0")
    @Max(value = 25, message = "capidad maxima de asientos por mesa es 25")
    @Min(value = 0, message = "el numero debe ser mayor o igual a 0")
    private int numeroAsientos;

    @NotNull(message = "El estado de la mesa es obligatorio")
    private EstadoFacturacion estadoFacturacion;

    @NotNull(message = "El estado de la mesa es obligatorio")
    private EstadoClienteMesa estadoClienteMesa;

    @NotNull(message = "El estado de la mesa es obligatorio")
    private EstadoMesa estadoMesa;
}