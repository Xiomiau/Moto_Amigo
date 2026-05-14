/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionbd;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author xiomi
 */
public class ConexionBD {
    
    private static ConexionBD conexion;
    private MongoClient cliente;
    private MongoDatabase bd;
    
    private ConexionBD() {
        this.cliente = MongoClients.create("mongodb://localhost:27017");
        this.bd= cliente.getDatabase("motoamigoBD");
    }
    
    public static ConexionBD obtenerConexion() {
        if(conexion == null) {
            conexion = new ConexionBD();
        }
        System.out.println("conexion exitosa");
        return conexion;
        
    }
    
    public MongoDatabase getBd() {
        return bd;
    }
    
    
}
