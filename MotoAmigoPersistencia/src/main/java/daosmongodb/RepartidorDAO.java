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
import entities.CuentaBancaria;
import entities.Documento;
import entities.Repartidor;
import enums.EstadoRepartidor;
import enums.TipoTransporte;
import java.util.ArrayList;
import org.bson.Document;
import java.util.List;
import java.util.UUID;
import org.bson.types.Binary;

/**
 *
 * @author xiomi
 */
public class RepartidorDAO implements IRepartidorDAO {

    private MongoCollection<Document> coleccion;

    public RepartidorDAO() {
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
        if (repartidor.getId() == null || repartidor.getId().isEmpty()) {
            repartidor.setId(UUID.randomUUID().toString());
        }

        Document doc = new Document()
                .append("id", repartidor.getId())
                .append("nombreCompleto", repartidor.getNombreCompleto())
                .append("correoElectronico", repartidor.getCorreoElectronico())
                .append("telefono", repartidor.getTelefono())
                .append("contrasenia", repartidor.getContrasenia())
                .append("estado", repartidor.getEstado().toString())
                .append("tipoTransporte", repartidor.getTipoTransporte() != null
                        ? repartidor.getTipoTransporte().toString() : null)
                .append("fechaRegistro", repartidor.getFechaRegistro())
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
        System.out.println("MONGO GUARDADO");
        return repartidor;
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
        Document filtro = new Document("id", repartidor.getId());

    Document actualizacion = new Document("$set", new Document()
            .append("nombreCompleto", repartidor.getNombreCompleto())
            .append("correoElectronico", repartidor.getCorreoElectronico())
            .append("telefono", repartidor.getTelefono())
            .append("contrasenia", repartidor.getContrasenia())
            .append("estado", repartidor.getEstado().toString())
            .append("tipoTransporte", repartidor.getTipoTransporte() != null
                    ? repartidor.getTipoTransporte().toString() : null)
            .append("fechaRegistro", repartidor.getFechaRegistro())
    );

    coleccion.updateOne(filtro, actualizacion);
    System.out.println("MONGO ACTUALIZADO: " + repartidor.getId());
        

    }

    @Override
    public List<Repartidor> listarTodos() throws PersistenciaException {
        List<Repartidor> repartidores = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            repartidores.add(documentoARepartidor(doc));
        }
        return repartidores;
    }

    private Repartidor documentoARepartidor(Document doc) {Repartidor r = new Repartidor();
    r.setId(doc.getString("id"));
    r.setNombreCompleto(doc.getString("nombreCompleto"));
    r.setCorreoElectronico(doc.getString("correoElectronico"));
    r.setTelefono(doc.getString("telefono"));
    r.setContrasenia(doc.getString("contrasenia"));
    r.setFechaRegistro(doc.getDate("fechaRegistro"));

    String estadoStr = doc.getString("estado");
    if (estadoStr != null) r.setEstado(EstadoRepartidor.valueOf(estadoStr));

    String transporteStr = doc.getString("tipoTransporte");
    if (transporteStr != null) r.setTipoTransporte(TipoTransporte.valueOf(transporteStr));

    Document docCuenta = (Document) doc.get("cuentaBancaria");
    if (docCuenta != null) {
        CuentaBancaria cb = new CuentaBancaria();
        cb.setNumeroCuenta(docCuenta.getString("numeroCuenta"));
        cb.setBanco(docCuenta.getString("banco"));
        cb.setClabe(docCuenta.getString("clabe"));
        r.setCuentaBancaria(cb);
    }

    Document docDocumento = (Document) doc.get("documento");
    if (docDocumento != null) {
        Documento d = new Documento();
        Binary ine = docDocumento.get("ine", Binary.class);
        if (ine != null) d.setIne(ine.getData());
        Binary foto = docDocumento.get("fotoPerfil", Binary.class);
        if (foto != null) d.setFotoPerfil(foto.getData());
        Binary ant = docDocumento.get("antecedentes", Binary.class);
        if (ant != null) d.setAntecedentes(ant.getData());
        Binary lic = docDocumento.get("licenciaConducir", Binary.class);
        if (lic != null) d.setLicenciaConducir(lic.getData());
        Binary tar = docDocumento.get("tarjetaCirculacion", Binary.class);
        if (tar != null) d.setTarjetaCirculacion(tar.getData());
        r.setDocumento(d);
    }

    return r;
    }
}
