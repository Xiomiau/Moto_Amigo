package com.mycompany.motoamigocontrol;

import com.mycompany.motoamigodto.EmprendedorDTO;
import com.mycompany.motoamigodto.PedidoDTO;
import com.mycompany.motoamigodto.RepartidorDTO;
import com.mycompany.motoamigonegocio.GestorSesionCU;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.solicitarpedidocu.SolicitarPedidoCU;
import java.util.List;

public class ControladorPrincipal {

    // El Controlador solo conoce a la capa de Negocio (Casos de Uso)
    private SolicitarPedidoCU solicitarPedidoCU;
    private GestorSesionCU gestorSesionCU;

    public ControladorPrincipal() {
        this.solicitarPedidoCU = new SolicitarPedidoCU();
        this.gestorSesionCU = new GestorSesionCU();
    }

    //MÉTODOS DEL EMPRENDEDOR
    public EmprendedorDTO obtenerEmprendedorLogueado() throws Exception {
        return gestorSesionCU.obtenerEmprendedorLogueado();
    }

    public PedidoDTO solicitarNuevoPedido(PedidoDTO pedidoDTO) throws NegocioException, Exception {
        return solicitarPedidoCU.publicarPedido(pedidoDTO);
    }

    //MÉTODOS DEL REPARTIDOR
    public RepartidorDTO obtenerRepartidorLogueado() throws Exception {
        return gestorSesionCU.obtenerRepartidorLogueado();
    }

    public List<PedidoDTO> consultarPedidosDisponibles() throws Exception {
        return solicitarPedidoCU.consultarPedidosDisponibles();
    }

    public void aceptarPedido(String idPedido, String idRepartidor) throws Exception {
        solicitarPedidoCU.aceptarPedido(idPedido, idRepartidor);
    }

    public void confirmarRecoleccionPedido(String idPedido) throws Exception {
        solicitarPedidoCU.confirmarRecoleccion(idPedido);
    }

    public void marcarPedidoComoEntregado(String idPedido) throws Exception {
        solicitarPedidoCU.marcarComoEntregado(idPedido);
    }
    
    public PedidoDTO obtenerPedidoPorId(String idPedido) throws Exception {
        return solicitarPedidoCU.obtenerPedidoPorId(idPedido); 
    }
}
