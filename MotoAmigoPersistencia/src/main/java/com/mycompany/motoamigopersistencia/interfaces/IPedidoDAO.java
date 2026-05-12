package com.mycompany.motoamigopersistencia.interfaces;
import entities.Pedido;
import com.mycompany.motoamigopersistencia.daos.PersistenciaException;
import java.util.List;

public interface IPedidoDAO {
    void guardarPedido(Pedido pedido) throws PersistenciaException;
    void actualizarPedido(Pedido pedido) throws PersistenciaException;
    Pedido buscarPedidoPorId(String idPedido) throws PersistenciaException;
    List<Pedido> consultarPedidosDisponibles() throws PersistenciaException;
}