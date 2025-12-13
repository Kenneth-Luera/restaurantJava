package com.example.Restaurant.controller;

import com.example.Restaurant.service.MesasService;
import com.example.Restaurant.dto.MesasDTO;
import com.example.Restaurant.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/mesas")
public class MesasController {

    @Autowired
    private MesasService mesasService;

    @PostMapping("/registrar")
    public ResponseEntity<MesasDTO> registrarMesas(@Valid @RequestBody MesasDTO mesasDTO){
        System.out.println("🔹 JSON recibido:");
        System.out.println(mesasDTO);   
        System.out.println("📩 JSON recibido: " + mesasDTO);
        
        MesasDTO nuevaMesa = mesasService.registrarMesas(mesasDTO);
        return ResponseEntity.ok(nuevaMesa);
    }

    @GetMapping
    public ResponseEntity<List<MesasDTO>> listarMesas(){
        List<MesasDTO> mesasDTOs = mesasService.listarMesas();
        return ResponseEntity.ok(mesasDTOs);
    }

    @GetMapping("/buscar/id/{idMesas}")
    public ResponseEntity<?> buscarPorID(@PathVariable UUID idMesas) {
        Optional<MesasDTO> mesasDTO = mesasService.buscarPorID(idMesas);
        return mesasDTO.isPresent() ? ResponseEntity.ok(mesasDTO.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mesa no encontrada");
    }

    @GetMapping("/buscar/numeroMesa/{numeroMesa}")
    public ResponseEntity<?> buscarPorNumero(@PathVariable int numeroMesa) {
        Optional<MesasDTO> mesasDTO = mesasService.buscarPorNumero(numeroMesa);
        return mesasDTO.isPresent() ? ResponseEntity.ok(mesasDTO.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mesa no encontrada");
    }
    @PutMapping("/actualizar/{idMesas}")
    public ResponseEntity<MesasDTO> actualizarMesa(@PathVariable UUID idMesas,@RequestBody MesasDTO mesasDTO){
            try{
                MesasDTO mesaActualizada = mesasService.actualizarMesa(idMesas, mesasDTO);
                if(mesaActualizada != null){
                    return new ResponseEntity<>(mesaActualizada, HttpStatus.OK);
                }else{
                    throw new ResourceNotFoundException("Mesa no encontrada");
                }
            }catch(Exception exception){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

    @DeleteMapping("/{idMesas}")
    public ResponseEntity<Void> eliminarMesa(@PathVariable UUID idMesas) {
        try{
            mesasService.eliminarMesa(idMesas);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //@GetMapping("/estado/{estado}")
    //public ResponseEntity<List<MesasDTO>> ListarMesasPorEstado(@PathVariable EstadoMesa estado) {
    //    List<MesasDTO> mesasDTOs = mesasService.obtenerMesasPorEstado(estado);
    //    return ResponseEntity.ok(mesasDTOs);
    //}

}

