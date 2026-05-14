/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daosmongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.motoamigopersistencia.daos.PersistenciaException;
import conexionbd.ConexionBD;
import com.mycompany.motoamigopersistencia.interfaces.IRepartidorDAO;
import entities.Repartidor;
import java.util.ArrayList;
import org.bson.Document;
import java.util.List;

/**
 *
 * @author xiomi
 */
public class RepartidorDAOMongo implements IRepartidorDAO {

    private MongoCollection<Document> coleccion;

    public RepartidorDAOMongo() {
        MongoDatabase bd = ConexionBD.obtenerConexion().getBd();
        this.coleccion = bd.getCollection("repartidores");
    }

    @Override
    public boolean existeCorreo(String correo) throws PersistenciaException {
        Document doc = coleccion.find(new Document("correoElectronico", correo)).first();
        return doc != null;
    }

    @Override
    public Repartidor guardarRepartidor(Repartidor repartidor) throws PersistenciaException {
        Document doc = new Document()
                .append("id", repartidor.getId())
                .append("nombreCompleto", repartidor.getNombreCompleto())
                .append("correoElectronico", repartidor.getCorreoElectronico())
                .append("telefono", repartidor.getTelefono())
                .append("contrasenia", repartidor.getContrasenia())
                .append("estado", repartidor.getEstado().toString())
                .append("tipoTransporte", repartidor.getTipoTransporte() != null
                        ? repartidor.getTipoTransporte().toString() : null)
                .append("cuentaBancaria", repartidor.getCuentaBancaria() != null
                        ? new Document()
                                .append("numeroCuenta", repartidor.getCuentaBancaria().getNumeroCuenta())
                                .append("banco", repartidor.getCuentaBancaria().getBanco())
                                .append("clabe", repartidor.getCuentaBancaria().getClabe()) : null)
                .append("documento", repartidor.getDocumento() != null
                        ? new Document()
                                .append("ine", repartidor.getDocumento().getIne())
                                .append("fotoPerfil", repartidor.getDocumento().getFotoPerfil())
                                .append("antecedentes", repartidor.getDocumento().getAntecedentes())
                                .append("licenciaConducir", repartidor.getDocumento().getLicenciaConducir())
                                .append("tarjetaCirculacion", repartidor.getDocumento().getTarjetaCirculacion()) : null);

        coleccion.insertOne(doc);
        repartidor.setId(doc.getObjectId("_id").toString());
        System.out.println("MONGO GUARDADO");
        return repartidor;
    }

    @Override
    public Repartidor obtenerRepartidorSesion() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Repartidor buscarPorId(String id) throws PersistenciaException {
        Document doc = coleccion.find(new Document("id", id)).first();
        if (doc == null) {
            return null;
        }
        return documentoARepartidor(doc);
    }

    @Override
    public void actualizar(Repartidor repartidor) throws PersistenciaException {

    }

    @Override
    public List<Repartidor> listarTodos() throws PersistenciaException {
        List<Repartidor> repartidores = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            repartidores.add(documentoARepartidor(doc));
        }
        return repartidores;
    }

    private Repartidor documentoARepartidor(Document doc) {
        Repartidor r = new Repartidor();
        r.setId(doc.getString("id"));
        r.setNombreCompleto(doc.getString("nombreCompleto"));
        r.setCorreoElectronico(doc.getString("correoElectronico"));
        r.setTelefono(doc.getString("telefono"));
        r.setContrasenia(doc.getString("contrasenia"));
        return r;
    }
}
