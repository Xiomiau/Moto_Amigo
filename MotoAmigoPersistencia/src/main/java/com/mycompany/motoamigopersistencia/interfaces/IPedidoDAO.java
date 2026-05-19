package com.mycompany.motoamigopersistencia.interfaces;
import entities.Pedido;
import com.mycompany.motoamigopersistencia.exepciones.PersistenciaException;
import java.util.List;

public interface IPedidoDAO {
    Pedido guardarPedido(Pedido pedido) throws PersistenciaException;
    Pedido actualizarPedido(Pedido pedido) throws PersistenciaException;
    Pedido buscarPedidoPorId(String idPedido) throws PersistenciaException;
    List<Pedido> consultarPedidosDisponibles() throws PersistenciaException;
}
