package com.mycompany.motoamigopersistencia.daos;

import com.mycompany.motoamigopersistencia.interfaces.IPedidoDAO;
import entities.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO implements IPedidoDAO {
    
    private static List<Pedido> baseDatosPedidos = new ArrayList<>();

    @Override
    public void guardarPedido(Pedido pedido) throws PersistenciaException {
        if (pedido.getId() == null || pedido.getId().isEmpty()) {
            pedido.setId("PED-" + (baseDatosPedidos.size() + 1));
        }
        baseDatosPedidos.add(pedido);
        System.out.println("Pedido " + pedido.getId() + " publicado exitosamente.");
    }

    @Override
    public void actualizarPedido(Pedido pedidoActualizado) throws PersistenciaException {
        for (int i = 0; i < baseDatosPedidos.size(); i++) {
            if (baseDatosPedidos.get(i).getId().equals(pedidoActualizado.getId())) {
                baseDatosPedidos.set(i, pedidoActualizado);
                System.out.println("Pedido " + pedidoActualizado.getId() + " actualizado a estado: " + pedidoActualizado.getEstado());
                return;
            }
        }
        throw new PersistenciaException("Pedido no encontrado en la base de datos.");
    }

    @Override
    public Pedido buscarPedidoPorId(String idPedido) throws PersistenciaException {
        for (Pedido p : baseDatosPedidos) {
            if (p.getId().equals(idPedido)) return p;
        }
        return null;
    }
    @Override
    public List<Pedido> consultarPedidosDisponibles() throws PersistenciaException {
        List<Pedido> disponibles = new ArrayList<>();
        for (Pedido p : baseDatosPedidos) {
            // Solo devolvems los que están esperando repartidor
            if (p.getEstado() == enums.EstadoPedido.PUBLICADO) {
                disponibles.add(p);
            }
        }
        return disponibles;
    }
}