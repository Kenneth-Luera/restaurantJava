package com.example.Restaurant.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdenDTO {
    private UUID idOrden;

    @NotNull(message = "La mesa es obligatoria")
    @JsonProperty("idMesa")
    private UUID idMesa;

    @NotNull(message = "esta seccion no puede estar vacio")
    @JsonProperty("idPlatos")
    private List<UUID> idPlatos;

    @NotNull(message = "precio total no puede estar vacio")
    @JsonProperty("precioTotal")
    @Min(value = 0, message = "el monto no puede ser negativo")
    private BigDecimal precioTotal;
}