/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import entities.CuentaBancaria;
import enums.TipoTransporte;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.bos.CuentaBancariaBO;
import com.mycompany.motoamigonegocio.bos.DocumentoBO;

import com.mycompany.motoamigonegocio.bos.RepartidorBO;
import javax.swing.JOptionPane;
import util.guis.AreaCargaArchivo;
import util.guis.TarjetaTransporte;

/**
 *
 * @author xiomi
 */
public class ValidadorFormularios {

    private static final RepartidorBO repartidorBO = new RepartidorBO();
    private static final DocumentoBO documentoBO = new DocumentoBO();

    public boolean validarDatosPersonales(String nombre, String correo, String telefono, String pss) {

        try {
            repartidorBO.validarNombreCompleto(nombre);
            repartidorBO.validarCorreoElectronico(correo);
            repartidorBO.validarTelefono(telefono);
            repartidorBO.validarContrasena(pss);
            return true;
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return false; 
        }
    }

    public boolean validarDocumentosPersonales(AreaCargaArchivo ine, AreaCargaArchivo fotoPerfil, AreaCargaArchivo antecedentes) {
        try {
            documentoBO.validarINE(ine.getArchivoBytes());
            documentoBO.validarFotoPerfil(fotoPerfil.getArchivoBytes());
            documentoBO.validarAntecedentes(antecedentes.getArchivoBytes());
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Documentos incompletos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean validarDocumentosTransporte(AreaCargaArchivo licencia, AreaCargaArchivo tarjetaCirculacion, TarjetaTransporte auto, TarjetaTransporte bici) {

        if (!auto.isSeleccionada() && !bici.isSeleccionada()) {
            JOptionPane.showMessageDialog(null, "Sin tipo de transporte seleccionado.", "Favor de elegir un tipo de transporte", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (auto.isSeleccionada()) {
            try {
                documentoBO.validarLicencia(licencia.getArchivoBytes());
                documentoBO.validarTarjetaCirculacion(tarjetaCirculacion.getArchivoBytes());
                
            } catch (NegocioException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Documentos incompletos", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;

    }

}
