package com.example.Restaurant.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Restaurant.entity.EstadoPlato;
import com.example.Restaurant.entity.Platos;

@Repository
public interface PlatosRepository extends JpaRepository<Platos,UUID> {

    Optional<Platos> findByNombrePlato(String nombrePlato);

    Optional<Platos> findByIdPlato(UUID idPlato);

    List<Platos> findByEstadoPlato(EstadoPlato estadoPlato);
}

