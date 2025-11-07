package com.example.Restaurant.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Restaurant.dto.OrdenDTO;

public interface OrdenService {
    OrdenDTO crearOrden(OrdenDTO ordenDTO);

    List<OrdenDTO> ListarOrden();

    Optional<OrdenDTO> obtenerOrdenPorId(UUID idOrden);

    OrdenDTO actualizarOrden(UUID idOrden, OrdenDTO ordenDTO);

    void eliminarOrden(UUID idOrden);
}