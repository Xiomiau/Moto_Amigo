package controlador;

import com.mycompany.motoamigodto.PedidoDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.solicitarpedidocu.SolicitarPedidoCU;
import java.util.List;

public class ControladorPrincipal {

    private SolicitarPedidoCU solicitarPedidoCU;

    private static final String NOMBRE_REPARTIDOR_DEMO = "Juan Pérez Gómez";
    private static final String ID_REPARTIDOR_DEMO = "1";

    public ControladorPrincipal() {
        this.solicitarPedidoCU = new SolicitarPedidoCU();
    }

    // Emprendedor
    public PedidoDTO solicitarNuevoPedido(PedidoDTO pedidoDTO) throws NegocioException, Exception {
        return solicitarPedidoCU.publicarPedido(pedidoDTO);
    }

    // Repartidor
    public List<PedidoDTO> consultarPedidosDisponibles() throws Exception {
        return solicitarPedidoCU.consultarPedidosDisponibles();
    }

    public void aceptarPedido(String idPedido) throws Exception {
        solicitarPedidoCU.aceptarPedido(idPedido, ID_REPARTIDOR_DEMO);
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
    public String obtenerNombreRepartidor() {
    return NOMBRE_REPARTIDOR_DEMO;
}
}
