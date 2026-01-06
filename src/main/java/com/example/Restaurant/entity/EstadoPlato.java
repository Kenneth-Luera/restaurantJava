package com.example.Restaurant.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EstadoPlato {
    disponible, agotado;

    @JsonCreator
    public static EstadoPlato fromString(String value) {
        return EstadoPlato.valueOf(value.toUpperCase());
    }
}
