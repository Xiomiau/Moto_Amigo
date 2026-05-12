/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author xiomi
 */
public class Icon {
    public static ImageIcon cargarIcono(String ruta, int width, int height) {
        java.net.URL url = Icon.class.getResource(ruta);

        if (url == null) {
            System.out.println("No se encontró el icono: " + ruta);
            return null;
        }

        ImageIcon iconoOriginal = new ImageIcon(url);

        Image imagenEscalada = iconoOriginal.getImage()
            .getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(imagenEscalada);
    }
    
}
