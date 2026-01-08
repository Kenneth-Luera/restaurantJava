package com.example.Restaurant.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Restaurant.entity.Mesas;
import com.example.Restaurant.entity.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, UUID> {

    Optional<Orden> findTopByMesa_IdMesasOrderByIdOrdenDesc(UUID idMesa);
}

