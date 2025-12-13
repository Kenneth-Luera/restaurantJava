package com.example.Restaurant.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Restaurant.dto.OrdenDTO;
import com.example.Restaurant.entity.Mesas;
import com.example.Restaurant.entity.Orden;
import com.example.Restaurant.entity.Platos;
import com.example.Restaurant.exceptions.ResourceNotFoundException;
import com.example.Restaurant.mapper.OrdenMapper;
import com.example.Restaurant.repository.MesasRepository;
import com.example.Restaurant.repository.OrdenRepository;
import com.example.Restaurant.repository.PlatosRepository;
import com.example.Restaurant.service.OrdenService;

@Service
public class OrdenServiceImpl implements OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private MesasRepository mesasRepository;

    @Autowired
    private PlatosRepository platosRepository;

    @Autowired
    private OrdenMapper ordenMapper;

    @Override
    public OrdenDTO crearOrden(OrdenDTO ordenDTO) {

        Mesas mesa = mesasRepository.findByIdMesas(ordenDTO.getIdMesa())
                .orElseThrow(() -> new ResourceNotFoundException("Mesa no encontrada"));

        Orden orden = new Orden();
        orden.setMesa(mesa);

        orden.setPrecioTotal(BigDecimal.ZERO);

        Orden nuevaOrden = ordenRepository.save(orden);
        return ordenMapper.toDTO(nuevaOrden);
    }

    @Override
    public List<OrdenDTO> ListarOrden() {
        return ordenRepository.findAll().stream().map(ordenMapper::toDTO).toList();
    }

    @Override
    public Optional<OrdenDTO> obtenerOrdenPorId(UUID idOrden) {
        return ordenRepository.findById(idOrden).map(ordenMapper::toDTO);
    }

    @Override
    public OrdenDTO obtenerOrden(UUID idOrden) {
        Orden orden = ordenRepository.findById(idOrden)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));

        return ordenMapper.toDTO(orden);
    }

    @Override
    public OrdenDTO actualizarOrden(UUID idOrden, OrdenDTO ordenDTO) {

        Orden orden = ordenRepository.findById(idOrden)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));

        orden.setPrecioTotal(ordenDTO.getPrecioTotal());

        Orden actualizada = ordenRepository.save(orden);
        return ordenMapper.toDTO(actualizada);
    }

    @Override
    public void eliminarOrden(UUID idOrden) {
        Orden orden = ordenRepository.findById(idOrden)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));
        ordenRepository.delete(orden);
    }

    @Override
    public OrdenDTO agregarPlatosAOrden(UUID idOrden, List<UUID> idPlatos) {

        Orden orden = ordenRepository.findById(idOrden)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));

        List<Platos> platos = platosRepository.findAllById(idPlatos);

        orden.getPlatos().addAll(platos);

        BigDecimal total = orden.getPlatos().stream()
                .map(p -> BigDecimal.valueOf(p.getPrecioPlato()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        orden.setPrecioTotal(total);

        Orden actualizado = ordenRepository.save(orden);

        return ordenMapper.toDTO(actualizado);
    }
}
