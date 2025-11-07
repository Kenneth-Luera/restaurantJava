package com.example.Restaurant.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Restaurant.dto.PlatosDTO;

public interface PlatosService {
    PlatosDTO crearPlato(PlatosDTO platosDTO);

    List<PlatosDTO> ListarPlatos();

    Optional<PlatosDTO> obtenerPlatosPorId(UUID idPlato);

    PlatosDTO actualizarPlato(UUID idPlato, PlatosDTO platosDTO);

    void eliminarPlato(UUID idPlato);
}
