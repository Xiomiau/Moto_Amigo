package com.mycompany.solicitarpedidocu;

import com.mycompany.motoamigodto.PedidoDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.convertidores.ConvertidorPedido;
import com.mycompany.motoamigopersistencia.daos.PedidoDAO;
import com.mycompany.motoamigopersistencia.interfaces.IPedidoDAO;
import entities.Pedido;
import enums.EstadoPedido;


public class SolicitarPedidoCU {

    private IPedidoDAO pedidoDAO;
    private ConvertidorPedido convertidor;

    public SolicitarPedidoCU() {
        this.pedidoDAO = new PedidoDAO();
        this.convertidor = new ConvertidorPedido();
    }

    // 1. El emprendedor publica el pedido
    public PedidoDTO publicarPedido(PedidoDTO NuevoPedido) throws Exception {
        // Validación de reglas de negocio
        validarDatosPedido(NuevoPedido);

        // 1. Consultar API Externa (Simulada) para distancia y tiempo
        NuevoPedido.distanciaKm = simularCalculoDistanciaMapBox(NuevoPedido.direccionOrigen, NuevoPedido.direccionDestino);
        NuevoPedido.tiempoEstimadoMinutos = simularCalculoTiempoMapBox(NuevoPedido.distanciaKm);
        
        // 2. Aplicar Regla de Negocio Interna (Costo)
        NuevoPedido.costo = calcularCostoTarifa(NuevoPedido.distanciaKm);

        // Mapeo DTO a Entidad
        Pedido nuevoPedido = convertidor.mapearDtoAEntidad(NuevoPedido);
        nuevoPedido.setEstado(EstadoPedido.PUBLICADO);

        // Guardar
        pedidoDAO.guardarPedido(nuevoPedido);
        
        return convertidor.mapearEntidadADTO(nuevoPedido);
    }

    // 2. El Repartidor acepta el pedido
    public void aceptarPedido(String idPedido, String idRepartidor) throws Exception {
        Pedido pedido = pedidoDAO.buscarPedidoPorId(idPedido);
        
        if (pedido == null) {
            throw new NegocioException("El pedido no existe.");
        }
        if (pedido.getEstado() != EstadoPedido.PUBLICADO) {
            throw new NegocioException("El pedido ya no está disponible.");
        }
        
        pedido.setIdRepartidor(idRepartidor);
        pedido.setEstado(EstadoPedido.RECOLECCION);
        pedidoDAO.actualizarPedido(pedido);
    }

    // 3. El repartidor confirma que ya tiene el paquete en sus manos
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
        if (esNuloOVacio(dto.direccionOrigen) || esNuloOVacio(dto.direccionDestino) || 
            esNuloOVacio(dto.tipoPaquete) || esNuloOVacio(dto.idEmprendedor)) {
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
        // Aquí la API real devolvería la distancia real calculando las coordenadas.
        return 3.2; // 3.2 km como referencia estática para las pruebas
    }
    
    private int simularCalculoTiempoMapBox(double distancia) {
        return 15; // 15 minutos aprox
    }
    
    // Calculo de el pafo del Repartidor
    private double calcularCostoTarifa(double distanciaKm) {
        // Regla de negocio: $6.00 MXN por cada kilómetro recorrido
        return distanciaKm * 6.0 + 10;
    }
    
    // NUEVO: Devuelve la lista para la tabla del repartidor
    public java.util.List<PedidoDTO> consultarPedidosDisponibles() throws Exception {
        java.util.List<Pedido> pedidosEntidad = pedidoDAO.consultarPedidosDisponibles();
        java.util.List<PedidoDTO> pedidosDTO = new java.util.ArrayList<>();
        
        for (Pedido p : pedidosEntidad) {
            pedidosDTO.add(convertidor.mapearEntidadADTO(p));
        }
        return pedidosDTO;
    }
    
    // Buscar un pedido específico por su ID y devolver su DTO
    public PedidoDTO obtenerPedidoPorId(String idPedido) throws Exception {
        Pedido p = pedidoDAO.buscarPedidoPorId(idPedido);
        if (p == null) {
            return null;
        }
        return convertidor.mapearEntidadADTO(p);
    }
}
