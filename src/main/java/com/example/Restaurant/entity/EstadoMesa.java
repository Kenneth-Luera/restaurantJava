package com.example.Restaurant.entity;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum EstadoMesa {
    DISPONIBLE, NO_DISPONIBLE;

    @JsonCreator
    public static EstadoMesa fromString(String value) {
        return EstadoMesa.valueOf(value.toUpperCase());
    }
}
