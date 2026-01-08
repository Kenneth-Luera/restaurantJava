package com.example.Restaurant.service;

import java.util.List;
import java.util.UUID;

import com.example.Restaurant.dto.OrdenDTO;
import com.example.Restaurant.dto.OrdenItemDTO;

public interface OrdenService {

    OrdenDTO crearOrden(OrdenDTO ordenDTO);

    List<OrdenDTO> listarOrdenes();

    OrdenDTO obtenerOrdenPorId(UUID idOrden);

    OrdenDTO actualizarOrden(UUID idOrden, OrdenDTO ordenDTO);

    void eliminarOrden(UUID idOrden);

    OrdenDTO obtenerOrden(UUID idOrden);

    List<OrdenDTO> ListarOrden();

    OrdenDTO agregarPlatosAOrden(UUID idOrden, List<OrdenItemDTO> items);

    OrdenDTO obtenerUltimaOrdenPorMesa(UUID idMesa);
}
