package com.example.Restaurant.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Restaurant.dto.OrdenDTO;
import com.example.Restaurant.dto.OrdenItemDTO;
import com.example.Restaurant.service.OrdenService;

@CrossOrigin(origins = "*")
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

    @GetMapping("/ultima/mesa/{idMesa}")
    public ResponseEntity<OrdenDTO> obtenerUltimaOrdenPorMesa(
            @PathVariable UUID idMesa
    ) {
        return ResponseEntity.ok(
                ordenService.obtenerUltimaOrdenPorMesa(idMesa)
        );
    }


    @PostMapping("/{idOrden}/platos")
    public ResponseEntity<OrdenDTO> agregarPlatos(
            @PathVariable UUID idOrden,
            @RequestBody List<OrdenItemDTO> items
    ) {
        return ResponseEntity.ok(
            ordenService.agregarPlatosAOrden(idOrden, items)
        );
    }

    @PutMapping("/{idOrden}")
    public ResponseEntity<OrdenDTO> actualizarOrden(@PathVariable UUID idOrden, @RequestBody OrdenDTO ordenDTO) {
        try {
            OrdenDTO ordenActualizada = ordenService.actualizarOrden(idOrden, ordenDTO);
            return new ResponseEntity<>(ordenActualizada, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
