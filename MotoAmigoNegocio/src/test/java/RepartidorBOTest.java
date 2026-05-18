import com.mycompany.motoamigonegocio.NegocioException;
import com.mycompany.motoamigonegocio.bos.RepartidorBO;
import org.junit.Before;
import org.junit.Test;

public class RepartidorBOTest {

    private RepartidorBO bo;

    @Before
    public void setUp() {
        bo = new RepartidorBO();
    }

    // --- Nombre ---
    @Test
    public void validarNombre_valido_noLanzaExcepcion() throws NegocioException {
        bo.validarNombreCompleto("Luis Armando Vega Torres");
    }

    @Test(expected = NegocioException.class)
    public void validarNombre_vacio_lanzaExcepcion() throws NegocioException {
        bo.validarNombreCompleto("");
    }

    @Test(expected = NegocioException.class)
    public void validarNombre_null_lanzaExcepcion() throws NegocioException {
        bo.validarNombreCompleto(null);
    }

    // --- Correo ---
    @Test
    public void validarCorreo_valido_noLanzaExcepcion() throws NegocioException {
        bo.validarCorreoElectronico("luis@correo.com");
    }

    @Test(expected = NegocioException.class)
    public void validarCorreo_sinArroba_lanzaExcepcion() throws NegocioException {
        bo.validarCorreoElectronico("luiscorreo.com");
    }

    @Test(expected = NegocioException.class)
    public void validarCorreo_vacio_lanzaExcepcion() throws NegocioException {
        bo.validarCorreoElectronico("");
    }

    // --- Contraseña ---
    @Test
    public void validarContrasena_valida_noLanzaExcepcion() throws NegocioException {
        bo.validarContrasena("pass1234");
    }

    @Test(expected = NegocioException.class)
    public void validarContrasena_menosDe8_lanzaExcepcion() throws NegocioException {
        bo.validarContrasena("abc");
    }

    @Test(expected = NegocioException.class)
    public void validarContrasena_vacia_lanzaExcepcion() throws NegocioException {
        bo.validarContrasena("");
    }

    // --- Teléfono ---
    @Test
    public void validarTelefono_valido_noLanzaExcepcion() throws NegocioException {
        bo.validarTelefono("6441234567");
    }

    @Test(expected = NegocioException.class)
    public void validarTelefono_menosDe10_lanzaExcepcion() throws NegocioException {
        bo.validarTelefono("12345");
    }

    @Test(expected = NegocioException.class)
    public void validarTelefono_conLetras_lanzaExcepcion() throws NegocioException {
        bo.validarTelefono("644abc4567");
    }

    @Test(expected = NegocioException.class)
    public void validarTelefono_vacio_lanzaExcepcion() throws NegocioException {
        bo.validarTelefono("");
    }
}
