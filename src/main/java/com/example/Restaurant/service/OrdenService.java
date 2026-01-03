package com.example.Restaurant.service;

import java.util.List;
import java.util.UUID;

import com.example.Restaurant.dto.OrdenDTO;

public interface OrdenService {

    OrdenDTO crearOrden(OrdenDTO ordenDTO);

    List<OrdenDTO> listarOrdenes();

    OrdenDTO obtenerOrdenPorId(UUID idOrden);

    OrdenDTO actualizarOrden(UUID idOrden, OrdenDTO ordenDTO);

    void eliminarOrden(UUID idOrden);

    OrdenDTO agregarPlatosAOrden(UUID idOrden, List<UUID> idPlatos);

    OrdenDTO obtenerOrden(UUID idOrden);

    List<OrdenDTO> ListarOrden();
}
