package com.example.Restaurant.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EstadoFacturacion {
    PAGADO, NO_PAGADO;

    @JsonCreator
    public static EstadoFacturacion fromString(String value) {
        return EstadoFacturacion.valueOf(value.toUpperCase());
    }
}
