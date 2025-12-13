package com.example.Restaurant.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import com.example.Restaurant.dto.OrdenDTO;
import com.example.Restaurant.entity.Orden;
import com.example.Restaurant.entity.Platos;

@Component
public class OrdenMapper {

    @Autowired
    private ModelMapper modelMapper;

    public OrdenDTO toDTO(Orden orden) {
        OrdenDTO dto = modelMapper.map(orden, OrdenDTO.class);

        dto.setIdMesa(orden.getMesa().getIdMesas());

        dto.setIdPlatos(
            orden.getPlatos()
                .stream()
                .map(Platos::getIdPlato)
                .toList()
        );

        dto.setPrecioTotal(
            orden.getPrecioTotal() == null
                ? java.math.BigDecimal.ZERO
                : orden.getPrecioTotal()
        );

        return dto;
    }

    public Orden toEntity(OrdenDTO dto) {
        Orden orden = modelMapper.map(dto, Orden.class);

        orden.setMesa(null);
        orden.setPlatos(null);

        orden.setPrecioTotal(dto.getPrecioTotal());

        return orden;
    }
}
