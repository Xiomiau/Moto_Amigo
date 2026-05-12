package lanzador;

import javax.swing.SwingUtilities;
import pantallasregistrarrepartidor.GuiFormUno;
import pantallasvistaemprendedor.PanelEmprendedor;
import pantallasvistarepartidor.panelRepartidor;

public class Main {
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(() -> {
        new GuiFormUno().setVisible(true);
    });
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    // control
//                    ControladorPrincipal controlador = new ControladorPrincipal();
//
//                    // Emprendedor
//                    PanelEmprendedor formEmprendedor = new PanelEmprendedor(controlador);
//                    formEmprendedor.setLocation(150, 150); 
//                    formEmprendedor.setVisible(true);
//
//                    // Repartidor
//                    panelRepartidor formRepartidor = new panelRepartidor(controlador);
//                    formRepartidor.setLocation(700, 150); 
//                    formRepartidor.setVisible(true);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }
    
    
}
