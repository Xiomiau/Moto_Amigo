
import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.bos.DocumentoBO;
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
public class DocumentoBOTest {

    private DocumentoBO documentoBO;

    @Before
    public void setUp() {
        documentoBO = new DocumentoBO();
    }
    
    @Test
    public void validarINE_valido_noLanzaExcepcion() throws NegocioException {
        documentoBO.validarINE(new byte[]{1, 2, 3});
    }

    @Test(expected = NegocioException.class)
    public void validarINE_vacio_lanzaExcepcion() throws NegocioException {
        documentoBO.validarINE(new byte[]{});
    }

    @Test(expected = NegocioException.class)
    public void validarINE_null_lanzaExcepcion() throws NegocioException {
        documentoBO.validarINE(null);
    }

    @Test
    public void validarFotoPerfil_valida_noLanzaExcepcion() throws NegocioException {
        documentoBO.validarFotoPerfil(new byte[]{1, 2, 3});
    }

    @Test(expected = NegocioException.class)
    public void validarFotoPerfil_null_lanzaExcepcion() throws NegocioException {
        documentoBO.validarFotoPerfil(null);
    }

    @Test
    public void validarAntecedentes_validos_noLanzaExcepcion() throws NegocioException {
        documentoBO.validarAntecedentes(new byte[]{1, 2, 3});
    }

    @Test(expected = NegocioException.class)
    public void validarAntecedentes_null_lanzaExcepcion() throws NegocioException {
        documentoBO.validarAntecedentes(null);
    }

    @Test
    public void validarLicencia_valida_noLanzaExcepcion() throws NegocioException {
        documentoBO.validarLicencia(new byte[]{1, 2, 3});
    }

    @Test(expected = NegocioException.class)
    public void validarLicencia_null_lanzaExcepcion() throws NegocioException {
        documentoBO.validarLicencia(null);
    }

    @Test
    public void validarTarjeta_valida_noLanzaExcepcion() throws NegocioException {
        documentoBO.validarTarjetaCirculacion(new byte[]{1, 2, 3});
    }

    @Test(expected = NegocioException.class)
    public void validarTarjeta_null_lanzaExcepcion() throws NegocioException {
        documentoBO.validarTarjetaCirculacion(null);
    }

        
}
