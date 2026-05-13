/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import com.mycompany.motoamigonegocio.NegocioException;

/**
 *
 * @author xiomi
 */
public interface IDocumentoBO {
    void validarINE(byte[] ine) throws NegocioException;
    void validarFotoPerfil(byte[] foto) throws NegocioException;
    void validarAntecedentes(byte[] archivo) throws NegocioException;
    void validarLicencia(byte[] licencia) throws NegocioException;
    void validarTarjetaCirculacion(byte[] tarjeta) throws NegocioException;
    
}
