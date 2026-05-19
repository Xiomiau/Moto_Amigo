/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daosmongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.motoamigopersistencia.exepciones.PersistenciaException;
import com.mycompany.motoamigopersistencia.interfaces.IPedidoDAO;
import conexionbd.ConexionBD;
import entities.Pedido;
import enums.EstadoPedido;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bson.Document;

/**
 *
 * @author xiomi
 */
public class PedidoDAO implements IPedidoDAO {

    private MongoCollection<Document> coleccion;

    public PedidoDAO() {
        MongoDatabase bd = ConexionBD.obtenerConexion().getBd();
        this.coleccion = bd.getCollection("pedidos");
    }

    @Override
    public Pedido guardarPedido(Pedido pedido) throws PersistenciaException {

        if (pedido.getId() == null || pedido.getId().isEmpty()) {
            pedido.setId("PED- " + UUID.randomUUID().toString());
        }

        Document doc = new Document()
                .append("id", pedido.getId())
                .append("origen", pedido.getDireccionOrigen())
                .append("destino", pedido.getDireccionDestino())
                .append("tipo_paquete", pedido.getTipoPaquete())
                .append("peso_aproximado", pedido.getPesoAproximado())
                .append("descripcion", pedido.getDescripcionPaquete())
                .append("distancia", pedido.getDistanciaKm())
                .append("tiempo_estimado", pedido.getTiempoEstimadoMinutos())
                .append("id_repartidor", pedido.getIdRepartidor())
                .append("id_emprendedor", pedido.getIdEmprendedor())
                .append("estado", pedido.getEstado().toString());

        coleccion.insertOne(doc);
        return pedido;

    }

    @Override
    public Pedido actualizarPedido(Pedido pedido) throws PersistenciaException {

        Document buscar = new Document("id", pedido.getId());

        Document actualizar = new Document("$set", new Document()
                .append("estado", pedido.getEstado().toString())
                .append("id_repartidor", pedido.getIdRepartidor())
        );
        coleccion.updateOne(buscar, actualizar);
        return pedido;
    }

    @Override
    public Pedido buscarPedidoPorId(String idPedido) throws PersistenciaException {

        Document buscar = coleccion.find(new Document("id", idPedido)).first();
        if (buscar == null) {
            return null;
        }

        return documentoAPedido(buscar);
    }

    @Override
    public List<Pedido> consultarPedidosDisponibles() throws PersistenciaException {

        List<Pedido> pedidos = new ArrayList<>();
        Document filtro = new Document("estado", EstadoPedido.PUBLICADO.toString());

        for (Document doc : coleccion.find(filtro)) {
            pedidos.add(documentoAPedido(doc));

        }

        return pedidos;

    }

    private Pedido documentoAPedido(Document doc) {
        Pedido pedido = new Pedido();

        pedido.setId(doc.getString("id"));
        pedido.setDireccionOrigen(doc.getString("origen"));
        pedido.setDireccionDestino(doc.getString("destino"));
        pedido.setTipoPaquete(doc.getString("tipo_paquete"));
        pedido.setPesoAproximado(doc.getDouble("peso_aproximado"));
        pedido.setDescripcionPaquete(doc.getString("descripcion"));
        pedido.setDistanciaKm(doc.getDouble("distancia"));
        pedido.setTiempoEstimadoMinutos(doc.getInteger("tiempo_estimado"));
        pedido.setIdRepartidor(doc.getString("id_repartidor"));
        pedido.setIdEmprendedor(doc.getString("id_emprendedor"));

        String estadoStr = doc.getString("estado");
        if (estadoStr != null) {
            pedido.setEstado(EstadoPedido.valueOf(estadoStr));
        }

        return pedido;
    }

}
