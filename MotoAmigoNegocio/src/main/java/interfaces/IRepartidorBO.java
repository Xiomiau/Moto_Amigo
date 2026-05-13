
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
public interface IRepartidorBO {
    void validarNombreCompleto(String nombre) throws NegocioException;
    void validarCorreoElectronico(String correo) throws NegocioException;
    void validarContrasena(String contrasena) throws NegocioException;
    void validarTelefono(String telefono) throws NegocioException;
    boolean verificarCorreoDuplicado(String correo, java.util.List<entities.Repartidor> lista);
    
}
