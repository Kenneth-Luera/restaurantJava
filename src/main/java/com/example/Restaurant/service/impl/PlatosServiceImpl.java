package com.example.Restaurant.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Restaurant.dto.PlatosDTO;
import com.example.Restaurant.entity.Platos;
import com.example.Restaurant.exceptions.ResourceNotFoundException;
import com.example.Restaurant.mapper.PlatosMapper;
import com.example.Restaurant.repository.PlatosRepository;
import com.example.Restaurant.service.PlatosService;

public class PlatosServiceImpl implements PlatosService{

    @Autowired
    private PlatosRepository platosRepository;

    @Autowired
    private PlatosMapper platosMapper;

    @Override
    public PlatosDTO crearPlato(PlatosDTO platosDTO) {
        Platos platos = platosMapper.toEntity(platosDTO);
        Platos platosGuardado = platosRepository.save(platos);
        return platosMapper.toDTO(platosGuardado);
    }

    @Override
    public List<PlatosDTO> ListarPlatos() {
        List<Platos> platos = platosRepository.findAll();
        return platos.stream()
                .map(platosMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<PlatosDTO> obtenerPlatosPorId(UUID idPlato) {
        Optional<Platos> platos = platosRepository.findByIdPlato(idPlato);
        return platos.map(platosMapper::toDTO);
    }

    @Override
    public PlatosDTO actualizarPlato(UUID idPlato, PlatosDTO platosDTO) {
        Platos platoExistente = platosRepository.findByIdPlato(idPlato)
            .orElseThrow(() -> new ResourceNotFoundException("Plato no encontrado"));
    
        platoExistente.setNombrePlato(platosDTO.getNombrePlato());
        Platos platoActualizado = platosRepository.save(platoExistente);
        return platosMapper.toDTO(platoActualizado);
    }

    @Override
    public void eliminarPlato(UUID idPlato) {
        Optional<Platos> platoExistente = platosRepository.findByIdPlato(idPlato);
        if(!platoExistente.isPresent()){
            throw new ResourceNotFoundException("plato no encontrado para eliminar.");
        }
        platosRepository.deleteById(idPlato);
    }
    

}
