package com.example.Restaurant.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.example.Restaurant.entity.Mesas;
import com.example.Restaurant.entity.Platos;

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

    @NotNull(message = "es obligatorio seleccionar el numero de mesa")
    private Mesas numeroMesa;

    @NotNull(message = "esta seccion no puede estar vacio")
    private List<Platos> platos; 

    @NotNull(message = "precio total no puede estar vacio")
    @Min(value = 0, message =  "el monto no puede ser negativo")
    private BigDecimal precioTotal;
}
