
import com.mycompany.motoamigodto.CuentaBancariaDTO;
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.bos.CuentaBancariaBO;
import org.junit.Before;
import org.junit.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author xiomi
 */
public class CuentaBancariaBOTest {
    private CuentaBancariaBO cuentaBO;

    @Before
    public void setUp() {
        cuentaBO = new CuentaBancariaBO();
    }
    
    @Test
    public void validarCLABE_valida_noLanzaExcepcion() throws NegocioException {
        cuentaBO.validarCLABE("012180012345678901");
    }

    @Test(expected = NegocioException.class)
    public void validarCLABE_menosDe18_lanzaExcepcion() throws NegocioException {
        cuentaBO.validarCLABE("12345");
    }

    @Test(expected = NegocioException.class)
    public void validarCLABE_conLetras_lanzaExcepcion() throws NegocioException {
        cuentaBO.validarCLABE("01218001234567890A");
    }

    @Test
    public void validarNumeroCuenta_valido_noLanzaExcepcion() throws NegocioException {
        cuentaBO.validarNumeroCuenta("1234567890");
    }

    @Test(expected = NegocioException.class)
    public void validarNumeroCuenta_menosDe10_lanzaExcepcion() throws NegocioException {
        cuentaBO.validarNumeroCuenta("12345");
    }

    @Test
    public void validarCuentaBancaria_completa_noLanzaExcepcion() throws NegocioException {
        CuentaBancariaDTO dto = new CuentaBancariaDTO();
        dto.setNumeroCuenta("1234567890");
        dto.setClabe("012180012345678901");
        dto.setBanco("BBVA");
        cuentaBO.validarCuentaBancaria(dto);
    }

    @Test(expected = NegocioException.class)
    public void validarCuentaBancaria_null_lanzaExcepcion() throws NegocioException {
        cuentaBO.validarCuentaBancaria(null);
    }
    
}
