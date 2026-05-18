package lanzador;

import com.mycompany.motoamigonegocio.bos.AdministradorBO;
import com.mycompany.motoamigonegocio.bos.CuentaBancariaBO;
import com.mycompany.motoamigonegocio.bos.DocumentoBO;
import com.mycompany.motoamigonegocio.bos.RepartidorBO;
import com.mycompany.motoamigonegocio.convertidores.ConvertidorRepartidor;
import com.mycompany.motoamigopersistencia.interfaces.IRepartidorDAO;
import com.mycompany.registrarrepartidorcu.IRegistrarRepartidorCU;
import com.mycompany.registrarrepartidorcu.RegistrarRepartidorCU;
import control.ControlAdministradorRepartidor;
import daosmongodb.RepartidorDAO;
import interfaces.IAdministradorBO;
import interfaces.ICuentaBancariaBO;
import interfaces.IDocumentoBO;
import interfaces.IRepartidorBO;
import java.util.logging.Level;
import java.util.logging.Logger;
import pantallasadministrador.pantallaPrincipal;


public class Main {
    public static void main(String[] args) {
        
        // 1. DAO — la "bodega" ahora es MongoDB
        IRepartidorDAO repartidorDAO = new RepartidorDAO();
        
        // 2. BOs — las reglas de negocio
        IRepartidorBO repartidorBO = new RepartidorBO();
        IDocumentoBO documentoBO = new DocumentoBO();
        ICuentaBancariaBO cuentaBancariaBO = new CuentaBancariaBO();
        IAdministradorBO administradorBO = new AdministradorBO(repartidorDAO);
        
        // 3. Convertidor
        ConvertidorRepartidor convertidor = new ConvertidorRepartidor();
        
        // 4. CU con todo conectado
        IRegistrarRepartidorCU cu = new RegistrarRepartidorCU(
            repartidorBO, documentoBO, cuentaBancariaBO,
            repartidorDAO, convertidor, administradorBO
        );
        
        ControlAdministradorRepartidor controladorAdmin = new ControlAdministradorRepartidor(cu);

        
        // 5. Abrir primera pantalla
        java.awt.EventQueue.invokeLater(() -> {
            //new GuiFormUno(cu).setVisible(true);
            try {
                new pantallaPrincipal(controladorAdmin).setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
