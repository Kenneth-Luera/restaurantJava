package com.example.Restaurant.dto;


import java.util.UUID;

import com.example.Restaurant.entity.Categoria;
import com.example.Restaurant.entity.Estado;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlatosDTO {

    private UUID idPlato;

    @NotNull(message = "el nombre del plato es obligatorio")
    private String nombrePlato;

    @NotNull(message = "el precio del plato es obligatorio")
    @Min(value = 0, message = "el valor del plato no puede ser menor a 0")
    private Double precioPlato;

    @NotNull(message = "la cantidad de platos es informacion necesaria")
    @Min(value = 0, message = "la cantidad de platos siempre tiene que ser mayor a 0")
    private int cantidadPlatos;

    @NotNull(message = "es obligatorio seleccionar la categoria")
    private Categoria categoria;

    @NotNull(message = "es obligatorio seleccionar el estado del plato")
    private Estado estado;
}
