package com.example.Restaurant.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Restaurant.dto.MesasDTO;
import com.example.Restaurant.entity.EstadoClienteMesa;
import com.example.Restaurant.entity.EstadoFacturacion;
import com.example.Restaurant.entity.EstadoMesa;
import com.example.Restaurant.entity.Mesas;
import com.example.Restaurant.exceptions.ResourceNotFoundException;
import com.example.Restaurant.repository.MesasRepository;
import com.example.Restaurant.mapper.MesasMapper;
import com.example.Restaurant.service.MesasService;

@Service
public class MesasServiceImpl implements MesasService{

    @Autowired
    private MesasRepository mesasRespository;

    @Autowired
    private MesasMapper mesasMapper;

    @Override
    public MesasDTO registrarMesas(MesasDTO mesasDTO) {
        Mesas mesas = mesasMapper.toEntity(mesasDTO);
        
        Mesas mesasGuardadas = mesasRespository.save(mesas);
        return mesasMapper.toDTO(mesasGuardadas);
    }

    @Override
    public List<MesasDTO> listarMesas() {
        List<Mesas> mesas = mesasRespository.findAll();
        return mesas.stream()
                .map(mesasMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MesasDTO> buscarPorID(UUID idMesas) {
        Optional<Mesas> mesas = mesasRespository.findByIdMesas(idMesas);
        return mesas.map(mesasMapper::toDTO);
    }

    @Override
    public Optional<MesasDTO> buscarPorNumero(int numeroMesa) {
        Optional<Mesas> mesas = mesasRespository.findByNumeroMesa(numeroMesa);
        return mesas.map(mesasMapper::toDTO);
    }

    @Override
    public MesasDTO actualizarMesa(UUID idMesa, MesasDTO mesasDTO) {
        Mesas mesaExistente = mesasRespository.findByIdMesas(idMesa)
            .orElseThrow(() -> new ResourceNotFoundException("mesa no encontrada"));

        mesaExistente.setNumeroMesa(mesasDTO.getNumeroMesa());
        Mesas mesaActualizada = mesasRespository.save(mesaExistente);
        return mesasMapper.toDTO(mesaActualizada);
    }

    @Override
    public void eliminarMesa(UUID idMesa) {
        Optional<Mesas> mesasExistentes = mesasRespository.findByIdMesas(idMesa);
        if(!mesasExistentes.isPresent()){
            throw new ResourceNotFoundException( "mesa no encontrada");
        }
        mesasRespository.deleteById(idMesa);
    }

    @Override
    public List<MesasDTO> obtenerMesasDisponibles() {
        List<Mesas> mesas = mesasRespository.findByEstadoMesa(null);
        return mesas.stream()
                .map(mesasMapper::toDTO)
                .toList();
    }

    @Override
    public MesasDTO cambiarEstadoClienteMesa(UUID idMesa, EstadoClienteMesa nuevoEstado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cambiarEstadoClienteMesa'");
    }

    @Override
    public List<MesasDTO> obtenerMesasPorEstado(EstadoMesa estadoMesa) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerMesasPorEstado'");
    }

    @Override
    public List<MesasDTO> obtenerMesasPorEstadoFacturacion(EstadoFacturacion estadoFacturacion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerMesasPorEstadoFacturacion'");
    }

    @Override
    public List<MesasDTO> obtenerMesasPorEstadoCliente(EstadoClienteMesa estadoClienteMesa) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerMesasPorEstadoCliente'");
    }

    @Override
    public boolean existePorNumeroMesa(int numeroMesa) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existePorNumeroMesa'");
    }
}