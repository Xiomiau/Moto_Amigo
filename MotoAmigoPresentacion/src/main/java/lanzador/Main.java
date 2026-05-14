package lanzador;

import com.mycompany.motoamigonegocio.bos.AdministradorBO;
import com.mycompany.motoamigonegocio.bos.CuentaBancariaBO;
import com.mycompany.motoamigonegocio.bos.DocumentoBO;
import com.mycompany.motoamigonegocio.bos.RepartidorBO;
import com.mycompany.motoamigonegocio.convertidores.ConvertidorRepartidor;
import com.mycompany.motoamigopersistencia.interfaces.IRepartidorDAO;
import com.mycompany.registrarrepartidorcu.IRegistrarRepartidorCU;
import com.mycompany.registrarrepartidorcu.RegistrarRepartidorCU;
import daosmongodb.RepartidorDAOMongo;
import interfaces.IAdministradorBO;
import interfaces.ICuentaBancariaBO;
import interfaces.IDocumentoBO;
import interfaces.IRepartidorBO;
import pantallasregistrarrepartidor.GuiFormUno;

public class Main {
    public static void main(String[] args) {
        
        // 1. DAO — la "bodega" ahora es MongoDB
        IRepartidorDAO repartidorDAO = new RepartidorDAOMongo();
        
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
        
        // 5. Abrir primera pantalla
        java.awt.EventQueue.invokeLater(() -> {
            new GuiFormUno(cu).setVisible(true);
        });
    }
}
