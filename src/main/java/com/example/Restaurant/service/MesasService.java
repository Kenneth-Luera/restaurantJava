package com.example.Restaurant.service;

import java.util.List;
import java.util.Optional;

import com.example.Restaurant.dto.MesasDTO;
import com.example.Restaurant.entity.EstadoClienteMesa;
import com.example.Restaurant.entity.EstadoFacturacion;
import com.example.Restaurant.entity.EstadoMesa;
import java.util.UUID;


public interface MesasService {

    MesasDTO registrarMesas(MesasDTO mesasDTO);

    List<MesasDTO> listarMesas();

    Optional<MesasDTO> buscarPorID(UUID idMesas);

    Optional<MesasDTO> buscarPorNumero(int numeroMesa);

    MesasDTO actualizarMesa(UUID idMesa, MesasDTO mesasDTO);

    void eliminarMesa(UUID idMesa);

    MesasDTO cambiarEstadoClienteMesa(UUID idMesa, EstadoClienteMesa nuevoEstado);

    List<MesasDTO> obtenerMesasPorEstado(EstadoMesa estadoMesa);

    List<MesasDTO> obtenerMesasPorEstadoFacturacion(EstadoFacturacion estadoFacturacion);

    List<MesasDTO> obtenerMesasPorEstadoCliente(EstadoClienteMesa estadoClienteMesa);

    List<MesasDTO> obtenerMesasDisponibles();

    boolean existePorNumeroMesa(int numeroMesa);

}
