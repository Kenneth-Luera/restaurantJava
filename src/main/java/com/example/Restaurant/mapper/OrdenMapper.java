package com.example.Restaurant.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import com.example.Restaurant.dto.OrdenDTO;
import com.example.Restaurant.entity.Orden;

@Component
public class OrdenMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Orden toEntity(OrdenDTO ordenDTO){
        return modelMapper.map(ordenDTO, Orden.class);
    }

    public void toEntity(OrdenDTO ordenDTO, Orden ordenExistentes){
        modelMapper.map(ordenDTO, ordenExistentes);
    }

    public OrdenDTO toDTO(Orden orden){
        return modelMapper.map(orden,OrdenDTO.class);
    }
}
