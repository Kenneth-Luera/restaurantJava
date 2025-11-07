package com.example.Restaurant.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Restaurant.dto.PlatosDTO;
import com.example.Restaurant.exceptions.ResourceNotFoundException;
import com.example.Restaurant.service.PlatosService;

import jakarta.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/platos")
public class PlatosController {

    @Autowired
    private PlatosService platosService;

    @PostMapping("/registrar")
    public ResponseEntity<PlatosDTO> crearPlato(@Valid @RequestBody PlatosDTO platosDTO){
        PlatosDTO nuevoPlato = platosService.crearPlato(platosDTO);
        return new ResponseEntity<>(nuevoPlato, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlatosDTO>> ListarPlatos(){
        List<PlatosDTO> platosDTOs = platosService.ListarPlatos();
        return new ResponseEntity<>(platosDTOs,HttpStatus.OK);
    }

    @GetMapping("/{idPlato}")
    public ResponseEntity<PlatosDTO> obtenerPlatoPorId(@PathVariable UUID idPlato){
        Optional<PlatosDTO> platosOptional = platosService.obtenerPlatosPorId(idPlato);
        if(platosOptional.isPresent()){
            return new ResponseEntity<>(platosOptional.get(),HttpStatus.OK);
        }else{
            throw new ResourceNotFoundException("Plato no encontrado");
        }
    }

    @PutMapping("/{idPlato}")
    public ResponseEntity<PlatosDTO> actualizarPlato(@PathVariable UUID idPlato,@RequestBody PlatosDTO platosDTO){
        try{
            PlatosDTO platoActualizado = platosService.actualizarPlato(idPlato, platosDTO);
            if(platoActualizado != null){
                return new ResponseEntity<>(platoActualizado,HttpStatus.OK);
            }else{
                throw new ResourceNotFoundException("Plato no encontrado para actualizar");
            }
        }catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idPlato}")
    public ResponseEntity<Void> eliminarPlato(@PathVariable UUID idPlato){
        try {
            platosService.eliminarPlato(idPlato);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
