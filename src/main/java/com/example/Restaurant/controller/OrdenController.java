package com.example.Restaurant.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Restaurant.dto.OrdenDTO;
import com.example.Restaurant.service.OrdenService;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @PostMapping("/registrar")
    public ResponseEntity<?> crearOrden(@RequestBody OrdenDTO ordenDTO) {
        System.out.println("🔹 JSON recibido:");
        System.out.println(ordenDTO);
        return new ResponseEntity<>(ordenService.crearOrden(ordenDTO), HttpStatus.CREATED);
    }

    @PostMapping("/{idOrden}/agregar-platos")
    public ResponseEntity<?> agregarPlatos(@PathVariable UUID idOrden,@RequestBody List<UUID> idPlatos) {
        System.out.println("🔹 JSON recibido para agregar platos a la orden " + idOrden + ":");
        System.out.println(idPlatos);
        
        return new ResponseEntity<>(ordenService.agregarPlatosAOrden(idOrden, idPlatos), HttpStatus.OK);
    }

    @GetMapping("/{idOrden}")
    public ResponseEntity<?> obtenerOrden(@PathVariable UUID idOrden) {
        return new ResponseEntity<>(ordenService.obtenerOrden(idOrden), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return new ResponseEntity<>(ordenService.ListarOrden(), HttpStatus.OK);
    }

    @DeleteMapping("/{idOrden}")
    public ResponseEntity<?> eliminar(@PathVariable UUID idOrden) {
        ordenService.eliminarOrden(idOrden);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
