package com.example.Restaurant.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import com.example.Restaurant.dto.MesasDTO;
import com.example.Restaurant.entity.Mesas;

@Component
public class MesasMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public Mesas toEntity(MesasDTO mesasDTO){
        return modelMapper.map(mesasDTO, Mesas.class);
    }

    public void toEntity(MesasDTO mesasDTO, Mesas mesasExistentes){
        modelMapper.map(mesasDTO,mesasExistentes);
    }

    public MesasDTO toDTO(Mesas mesas){
        return modelMapper.map(mesas,MesasDTO.class);
    }

}
