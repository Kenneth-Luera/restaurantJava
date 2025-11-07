package com.example.Restaurant.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import com.example.Restaurant.dto.PlatosDTO;
import com.example.Restaurant.entity.Platos;

@Component
public class PlatosMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public Platos toEntity(PlatosDTO platosDTO){
        return modelMapper.map(platosDTO, Platos.class);
    }

    public void toEntity(PlatosDTO platosDTO, Platos platosExistentes){
        modelMapper.map(platosDTO,platosExistentes);
    }

    public PlatosDTO toDTO(Platos platos){
        return modelMapper.map(platos,PlatosDTO.class);
    }

}
