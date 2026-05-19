/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.solicitarpedidocu;

import com.mycompany.motoamigodto.pedido.PedidoDTO;
import java.util.List;

/**
 *
 * @author xiomi
 */
public interface ISolicitarPedido {
    PedidoDTO publicarPedido(PedidoDTO pedido) throws Exception;
    
    void aceptarPedido(String idPedido, String idRepartidor) throws Exception;
    
    void confirmarRecoleccion(String idPedido) throws Exception;
    
    void marcarComoEntregado(String idPedido) throws Exception;
    
    List<PedidoDTO> consultarPedidosDisponibles() throws Exception;
    
    PedidoDTO obtenerPedidoPorId(String idPedido) throws Exception;
    
}
