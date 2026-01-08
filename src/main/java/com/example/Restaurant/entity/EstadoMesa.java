package com.example.Restaurant.entity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EstadoMesa {
    DISPONIBLE,
    PAGADO,
    NO_DISPONIBLE;

}
