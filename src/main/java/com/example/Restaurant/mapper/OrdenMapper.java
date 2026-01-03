package com.example.Restaurant.mapper;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import com.example.Restaurant.dto.OrdenDTO;
import com.example.Restaurant.dto.OrdenItemDTO;
import com.example.Restaurant.entity.Orden;
import com.example.Restaurant.entity.OrdenPlato;

@Component
public class OrdenMapper {

    public OrdenDTO toDTO(Orden orden) {

        OrdenDTO dto = new OrdenDTO();
        dto.setIdOrden(orden.getIdOrden());

        dto.setIdMesa(orden.getMesa().getIdMesas());
        dto.setNumeroMesa(orden.getMesa().getNumeroMesa());

        dto.setPrecioTotal(
            orden.getPrecioTotal() == null
                ? BigDecimal.ZERO
                : orden.getPrecioTotal()
        );

        List<OrdenItemDTO> items = orden.getItems()
            .stream()
            .map(this::toItemDTO)
            .toList();

        dto.setItems(items);

        return dto;
    }

    private OrdenItemDTO toItemDTO(OrdenPlato item) {

        OrdenItemDTO dto = new OrdenItemDTO();

        dto.setIdPlato(item.getPlato().getIdPlato());
        dto.setNombrePlato(item.getPlato().getNombrePlato());
        dto.setCantidad(item.getCantidad());
        dto.setPrecioUnitario(item.getPrecioUnitario());
        dto.setSubtotal(item.getSubtotal());

        return dto;
    }
}

