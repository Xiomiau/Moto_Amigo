package lanzador;

import administrador.PantallaPrincipal;
import com.mycompany.motoamigonegocio.bos.AdministradorBO;
import com.mycompany.motoamigonegocio.bos.CuentaBancariaBO;
import com.mycompany.motoamigonegocio.bos.DocumentoBO;
import com.mycompany.motoamigonegocio.bos.RepartidorBO;
import com.mycompany.motoamigonegocio.convertidores.ConvertidorRepartidor;
import com.mycompany.motoamigopersistencia.interfaces.IRepartidorDAO;
import com.mycompany.registrarrepartidorcu.IRegistrarRepartidorCU;
import com.mycompany.registrarrepartidorcu.RegistrarRepartidorCU;
import control.ControlAdministradorRepartidor;
import controlador.ControladorPrincipal;
import daosmongodb.RepartidorDAO;
import interfaces.IAdministradorBO;
import interfaces.ICuentaBancariaBO;
import interfaces.IDocumentoBO;
import interfaces.IRepartidorBO;
import java.util.logging.Level;
import java.util.logging.Logger;
import pedidorepartidor.PanelRepartidor;
import registrorepartidor.FormDatosPersonales_inicio;
import control.FormCrearCuenta;
import control.FormLogin;
import control.ControlAdministradorRepartidor;

public class Main {

    public static void main(String[] args) {

        IRepartidorDAO repartidorDAO = new RepartidorDAO();
        

        IRepartidorBO repartidorBO = new RepartidorBO();
        IDocumentoBO documentoBO = new DocumentoBO();
        ICuentaBancariaBO cuentaBancariaBO = new CuentaBancariaBO();
        IAdministradorBO administradorBO = new AdministradorBO(repartidorDAO);

        ConvertidorRepartidor convertidor = new ConvertidorRepartidor();

        IRegistrarRepartidorCU cu = new RegistrarRepartidorCU(repartidorBO, documentoBO, cuentaBancariaBO,repartidorDAO, convertidor, administradorBO);

        ControlAdministradorRepartidor controladorAdmin = new ControlAdministradorRepartidor(cu);
        ControladorPrincipal controladorPrincipal = new ControladorPrincipal();
        ControlAdministradorRepartidor adminRepartidor = new  ControlAdministradorRepartidor(cu);

        java.awt.EventQueue.invokeLater(() -> {
            
            try {
                //Inicio de app
                new FormLogin(cu,controladorAdmin).setVisible(true);

                // administrador
                //new PantallaPrincipal(controladorAdmin).setVisible(true);
                
                // Registro Repartidor
                //new FormDatosPersonales_inicio(cu).setVisible(true);

                //Emprendedor
                //new pedidoemprendedor.PanelSolicitarEntrega(controladorPrincipal).setVisible(true);

                // repartidor
//                PanelRepartidor repartidor = new PanelRepartidor(controladorPrincipal);
//                repartidor.setVisible(true);

            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
