package com.example.Restaurant.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Restaurant.entity.EstadoMesa;
import com.example.Restaurant.entity.Mesas;

@Repository
public interface MesasRespository extends JpaRepository<Mesas,UUID>{
    
    Optional<Mesas> findByNumeroMesa(int numeroMesa);

    Optional<Mesas> findByIdMesas(UUID idMesas);

    List<Mesas> findByEstadoMesa(EstadoMesa estadoMesa);

}
