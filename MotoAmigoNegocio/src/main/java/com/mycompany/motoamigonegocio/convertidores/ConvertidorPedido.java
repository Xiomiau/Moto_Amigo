package com.mycompany.motoamigonegocio.convertidores;

import enums.EstadoPedido;
import entities.Pedido;
import com.mycompany.motoamigodto.EstadoPedidoDTO;
import com.mycompany.motoamigodto.PedidoDTO;

public class ConvertidorPedido {

    public Pedido mapearDtoAEntidad(PedidoDTO dto) {
        Pedido entidad = new Pedido();
        entidad.setId(dto.id);
        entidad.setDireccionOrigen(dto.direccionOrigen);
        entidad.setDireccionDestino(dto.direccionDestino);
        entidad.setTipoPaquete(dto.tipoPaquete);
        entidad.setDescripcionPaquete(dto.descripcionPaquete);
        entidad.setPesoAproximado(dto.pesoAproximado);
        entidad.setIdEmprendedor(dto.idEmprendedor);
        
        // Asumiendo que estos datos se actualizarán después con la API
        entidad.setDistanciaKm(dto.distanciaKm);
        entidad.setTiempoEstimadoMinutos(dto.tiempoEstimadoMinutos);
        entidad.setCosto(dto.costo);
        
        return entidad;
    }

    public PedidoDTO mapearEntidadADTO(Pedido entidad) {
        PedidoDTO dto = new PedidoDTO();
        dto.id = entidad.getId();
        dto.direccionOrigen = entidad.getDireccionOrigen();
        dto.direccionDestino = entidad.getDireccionDestino();
        dto.tipoPaquete = entidad.getTipoPaquete();
        dto.descripcionPaquete = entidad.getDescripcionPaquete();
        dto.pesoAproximado = entidad.getPesoAproximado();
        dto.distanciaKm = entidad.getDistanciaKm();
        dto.tiempoEstimadoMinutos = entidad.getTiempoEstimadoMinutos();
        dto.costo = entidad.getCosto();
        dto.idEmprendedor = entidad.getIdEmprendedor();
        dto.idRepartidor = entidad.getIdRepartidor();
        
        if (entidad.getEstado() != null) {
            dto.estado = EstadoPedidoDTO.valueOf(entidad.getEstado().name());
        }
        return dto;
    }
}