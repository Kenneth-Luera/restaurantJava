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
    private UUID idMesa;
    private int numeroMesa;

    private List<OrdenItemDTO> items;

    private BigDecimal precioTotal;
}