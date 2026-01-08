package com.example.Restaurant.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EstadoFacturacion {
    PAGADO,
    NO_PAGADO;

    @JsonCreator
    public static EstadoFacturacion fromString(String value) {
        if (value == null) {
            return null;
        }
        return EstadoFacturacion.valueOf(value.toUpperCase());
    }
}
