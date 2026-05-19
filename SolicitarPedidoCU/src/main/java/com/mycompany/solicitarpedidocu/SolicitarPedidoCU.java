package com.mycompany.solicitarpedidocu;

import com.mycompany.motoamigodto.pedido.PedidoDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.convertidores.ConvertidorPedido;
import com.mycompany.motoamigopersistencia.interfaces.IPedidoDAO;
import daosmongodb.PedidoDAO;
import entities.Pedido;
import enums.EstadoPedido;
import java.util.List;

public class SolicitarPedidoCU implements ISolicitarPedido {

    private IPedidoDAO pedidoDAO;
    private ConvertidorPedido convertidor;

    public SolicitarPedidoCU() {
        this.pedidoDAO = new PedidoDAO();
        this.convertidor = new ConvertidorPedido();
    }

    // 1. El emprendedor publica el pedido
    @Override
    public PedidoDTO publicarPedido(PedidoDTO pedido) throws Exception {
        // Validación de reglas de negocio
        validarDatosPedido(pedido);

        pedido.distanciaKm = simularCalculoDistanciaMapBox(pedido.direccionOrigen, pedido.direccionDestino);
        pedido.tiempoEstimadoMinutos = simularCalculoTiempoMapBox(pedido.distanciaKm);

        // 2. Aplicar Regla de Negocio Interna (Costo)
        pedido.costo = calcularCostoTarifa(pedido.distanciaKm);

        // Mapeo DTO a Entidad
        Pedido nuevoPedido = convertidor.mapearDtoAEntidad(pedido);
        nuevoPedido.setEstado(EstadoPedido.PUBLICADO);

        // Guardar
        pedidoDAO.guardarPedido(nuevoPedido);

        return convertidor.mapearEntidadADTO(nuevoPedido);
    }

    // 2. El Repartidor acepta el pedido
    @Override
    public void aceptarPedido(String idPedido, String idRepartidor) throws Exception {        
        
        Pedido pedido = pedidoDAO.buscarPedidoPorId(idPedido);        
        pedido.setIdRepartidor(idRepartidor);
        System.out.println("ID repartidor seteado: " + pedido.getIdRepartidor());

        if (pedido == null) {
            throw new NegocioException("El pedido no existe.");
        }
        if (pedido.getEstado() != EstadoPedido.PUBLICADO) {
            throw new NegocioException("El pedido ya no está disponible.");
        }

        pedido.setIdRepartidor(pedido.getIdRepartidor());
        System.out.println("idrepartidor " + pedido.getIdRepartidor());
        pedido.setEstado(EstadoPedido.RECOLECCION);
        pedidoDAO.actualizarPedido(pedido);
    }

    // 3. El repartidor confirma que ya tiene el paquete en sus manos
    @Override
    public void confirmarRecoleccion(String idPedido) throws Exception {
        Pedido pedido = pedidoDAO.buscarPedidoPorId(idPedido);

        if (pedido == null) {
            throw new NegocioException("El pedido no existe.");
        }
        if (pedido.getEstado() != EstadoPedido.RECOLECCION) {
            throw new NegocioException("El pedido no está en estado de recolección.");
        }

        pedido.setEstado(EstadoPedido.REPARTO);
        pedidoDAO.actualizarPedido(pedido);
    }

    // 4. El repartidor entrega el paquete al cliente
    @Override
    public void marcarComoEntregado(String idPedido) throws Exception {
        Pedido pedido = pedidoDAO.buscarPedidoPorId(idPedido);

        if (pedido == null) {
            throw new NegocioException("El pedido no existe.");
        }
        if (pedido.getEstado() != EstadoPedido.REPARTO) {
            throw new NegocioException("No se puede entregar un paquete que no está en reparto.");
        }

        pedido.setEstado(EstadoPedido.ENTREGADO);
        pedidoDAO.actualizarPedido(pedido);
    }

    // metodos de validacion 
    private void validarDatosPedido(PedidoDTO dto) throws NegocioException {
        if (esNuloOVacio(dto.direccionOrigen) || esNuloOVacio(dto.direccionDestino)
                || esNuloOVacio(dto.tipoPaquete) || esNuloOVacio(dto.idEmprendedor)) {
            throw new NegocioException("Faltan datos obligatorios para registrar el pedido.");
        }
        if (dto.pesoAproximado <= 0) {
            throw new NegocioException("El peso del paquete no es válido.");
        }
    }

    private boolean esNuloOVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    // Simulador mapbox
    private double simularCalculoDistanciaMapBox(String origen, String destino) {
        return 3.2; // 3.2 km como referencia estática para las pruebas
    }

    private int simularCalculoTiempoMapBox(double distancia) {
        return 15; // 15 minutos aprox
    }

    // Calculo de el pago del Repartidor
    private double calcularCostoTarifa(double distanciaKm) {
        // Regla de negocio: $6.00 MXN por cada kilómetro recorrido
        return distanciaKm * 6.0 + 10;
    }

    // NUEVO: Devuelve la lista para la tabla del repartidor
    @Override
    public List<PedidoDTO> consultarPedidosDisponibles() throws Exception {
        java.util.List<Pedido> pedidosEntidad = pedidoDAO.consultarPedidosDisponibles();
        java.util.List<PedidoDTO> pedidosDTO = new java.util.ArrayList<>();

        for (Pedido p : pedidosEntidad) {
            pedidosDTO.add(convertidor.mapearEntidadADTO(p));
        }
        return pedidosDTO;
    }

    // Buscar un pedido específico por su ID y devolver su DTO
    @Override
    public PedidoDTO obtenerPedidoPorId(String idPedido) throws Exception {
        Pedido p = pedidoDAO.buscarPedidoPorId(idPedido);
        if (p == null) {
            return null;
        }
        return convertidor.mapearEntidadADTO(p);
    }
}
