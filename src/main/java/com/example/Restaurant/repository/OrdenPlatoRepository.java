package com.example.Restaurant.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Restaurant.entity.Orden;
import com.example.Restaurant.entity.OrdenPlato;
import com.example.Restaurant.entity.Platos;

@Repository
public interface OrdenPlatoRepository extends JpaRepository<OrdenPlato, UUID> {

    Optional<OrdenPlato> findByOrdenAndPlato(Orden orden, Platos plato);
}
