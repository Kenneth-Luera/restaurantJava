package com.example.Restaurant.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class OrdenItemDTO {

    private UUID idPlato;
    private String nombrePlato;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
}
