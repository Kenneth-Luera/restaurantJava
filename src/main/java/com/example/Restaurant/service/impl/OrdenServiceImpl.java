package com.example.Restaurant.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Restaurant.dto.OrdenDTO;
import com.example.Restaurant.dto.OrdenItemDTO;
import com.example.Restaurant.entity.EstadoFacturacion;
import com.example.Restaurant.entity.Mesas;
import com.example.Restaurant.entity.Orden;
import com.example.Restaurant.entity.OrdenPlato;
import com.example.Restaurant.entity.Platos;
import com.example.Restaurant.exceptions.ResourceNotFoundException;
import com.example.Restaurant.mapper.OrdenMapper;
import com.example.Restaurant.repository.MesasRepository;
import com.example.Restaurant.repository.OrdenRepository;
import com.example.Restaurant.repository.OrdenPlatoRepository;
import com.example.Restaurant.repository.PlatosRepository;
import com.example.Restaurant.service.OrdenService;
@Service
public class OrdenServiceImpl implements OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private MesasRepository mesasRepository;

    @Autowired
    private PlatosRepository platosRepository;

    @Autowired
    private OrdenPlatoRepository ordenPlatoRepository;

    @Autowired
    private OrdenMapper ordenMapper;

    @Override
    public OrdenDTO crearOrden(OrdenDTO ordenDTO) {

        Mesas mesa = mesasRepository.findByIdMesas(ordenDTO.getIdMesa())
                .orElseThrow(() -> new ResourceNotFoundException("Mesa no encontrada"));

        Orden orden = new Orden();
        orden.setMesa(mesa);

        orden.setEstadoFacturacion(EstadoFacturacion.NO_PAGADO);

        orden.setPrecioTotal(BigDecimal.ZERO);

        Orden guardada = ordenRepository.save(orden);

        return ordenMapper.toDTO(guardada);
    }


    @Override
    public List<OrdenDTO> ListarOrden() {
        return ordenRepository.findAll()
                .stream()
                .map(ordenMapper::toDTO)
                .toList();
    }

    @Override
    public OrdenDTO obtenerOrden(UUID idOrden) {
        Orden orden = ordenRepository.findById(idOrden)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));

        return ordenMapper.toDTO(orden);
    }

    @Override
    public OrdenDTO agregarPlatosAOrden(UUID idOrden, List<OrdenItemDTO> items) {

        Orden orden = ordenRepository.findById(idOrden)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));

        BigDecimal total = orden.getPrecioTotal();

        for (OrdenItemDTO dto : items) {

            Platos plato = platosRepository.findById(dto.getIdPlato())
                    .orElseThrow(() -> new ResourceNotFoundException("Plato no encontrado"));

            int cantidad = dto.getCantidad();

            if (cantidad <= 0) {
                throw new IllegalArgumentException("Cantidad inválida");
            }

            if (plato.getCantidadPlatos() < cantidad) {
                throw new IllegalArgumentException(
                        "Stock insuficiente para el plato: " + plato.getNombrePlato()
                );
            }

            OrdenPlato item = ordenPlatoRepository
                    .findByOrdenAndPlato(orden, plato)
                    .orElse(null);

            if (item != null) {
                item.setCantidad(item.getCantidad() + cantidad);
            } else {
                item = new OrdenPlato();
                item.setOrden(orden);
                item.setPlato(plato);
                item.setCantidad(cantidad);
                item.setPrecioUnitario(
                        BigDecimal.valueOf(plato.getPrecioPlato())
                );
                orden.getItems().add(item);
            }

            item.setSubtotal(
                    item.getPrecioUnitario()
                            .multiply(BigDecimal.valueOf(item.getCantidad()))
            );

            plato.setCantidadPlatos(
                    plato.getCantidadPlatos() - cantidad
            );
            platosRepository.save(plato);

            total = total.add(
                    item.getPrecioUnitario()
                            .multiply(BigDecimal.valueOf(cantidad))
            );
        }

        orden.setPrecioTotal(total);

        Orden guardada = ordenRepository.save(orden);

        return ordenMapper.toDTO(guardada);
    }

    @Override
    public OrdenDTO obtenerOrdenPorId(UUID idOrden) {
        Orden orden = ordenRepository.findById(idOrden)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));

        return ordenMapper.toDTO(orden);
    }

    @Override
    public OrdenDTO obtenerUltimaOrdenPorMesa(UUID idMesa) {

        return ordenRepository
                .findTopByMesa_IdMesasOrderByIdOrdenDesc(idMesa)
                .map(ordenMapper::toDTO)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No se encontraron órdenes para la mesa"
                        )
                );
    }



    @Override
    public OrdenDTO actualizarOrden(UUID idOrden, OrdenDTO ordenDTO) {
        Orden orden = ordenRepository.findById(idOrden)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));

        orden.setEstadoFacturacion(ordenDTO.getEstadoFacturacion());

        Orden actualizada = ordenRepository.save(orden);
        return ordenMapper.toDTO(actualizada);
    }

    @Override
    public void eliminarOrden(UUID idOrden) {

        Orden orden = ordenRepository.findById(idOrden)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));

        ordenRepository.delete(orden);
    }

    @Override
    public List<OrdenDTO> listarOrdenes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarOrdenes'");
    }
}
