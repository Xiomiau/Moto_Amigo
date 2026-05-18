/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import com.mycompany.motoamigodto.repartidor.RepartidorDTO;
import com.mycompany.registrarrepartidorcu.IRegistrarRepartidorCU;
import java.util.List;

/**
 *
 * @author xiomi
 */
public class ControlAdministradorRepartidor {
    
    private IRegistrarRepartidorCU registrarCU;
    

    public ControlAdministradorRepartidor(IRegistrarRepartidorCU registrarCU) {
        this.registrarCU = registrarCU;
       
    }

    public List<RepartidorDTO> listarRepartidores() throws Exception {
        return registrarCU.listarRepartidores();
    }

    public void aprobarRepartidor(String id) throws Exception {
        registrarCU.aprobarRepartidor(id);
    }

    public void rechazarRepartidor(String id) throws Exception {
        registrarCU.rechazarRepartidor(id);
    }
    
    public interface OnAccionCompletada {
        void ejecutar();
    }
    
    
}
