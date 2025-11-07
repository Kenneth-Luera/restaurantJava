package com.example.Restaurant.entity;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum EstadoClienteMesa {
    SIN_ORDEN, ORDEN_CREADA, ORDEN_ENTREGADA;

    @JsonCreator
    public static EstadoClienteMesa fromString(String value) {
        return EstadoClienteMesa.valueOf(value.toUpperCase());
    }
}
