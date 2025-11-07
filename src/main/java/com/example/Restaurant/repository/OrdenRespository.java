package com.example.Restaurant.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Restaurant.entity.Orden;

public interface OrdenRespository extends JpaRepository<Orden,UUID>{

    Optional<Orden> findByIdOrden(UUID idOrden);

    
}
