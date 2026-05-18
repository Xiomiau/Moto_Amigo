
import daosmongodb.RepartidorDAOMongo;
import entities.CuentaBancaria;
import entities.Documento;
import entities.Repartidor;
import enums.EstadoRepartidor;
import enums.TipoTransporte;
import java.util.Date;
import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author xiomi
 */
public class RepartidorDAOMongoTest {
    private RepartidorDAOMongo dao;

    @BeforeEach
    void setUp() {
        dao = new RepartidorDAOMongo();
    }

    @Test
    void guardarRepartidor_conDocumentoYCuenta_seRecuperaCompleto() throws Exception {
        // --- Armar el repartidor completo ---
        Repartidor r = new Repartidor();
        r.setNombreCompleto("Luis Armando Vega");
        r.setCorreoElectronico("luis.vega@test.com");
        r.setTelefono("6441234567");
        r.setContrasenia("pass1234");
        r.setFechaRegistro(new Date());
        r.setTipoTransporte(TipoTransporte.MOTO);

        CuentaBancaria cb = new CuentaBancaria();
        cb.setNumeroCuenta("1234567890");
        cb.setBanco("BBVA");
        cb.setClabe("012180012345678901");
        r.setCuentaBancaria(cb);

        Documento d = new Documento();
        d.setIne(new byte[]{1, 2, 3});
        d.setFotoPerfil(new byte[]{4, 5, 6});
        d.setAntecedentes(new byte[]{7, 8, 9});
        d.setLicenciaConducir(new byte[]{10, 11, 12});
        d.setTarjetaCirculacion(new byte[]{13, 14, 15});
        r.setDocumento(d);

        // --- Guardar ---
        Repartidor guardado = dao.guardarRepartidor(r);
        assertNotNull(guardado.getId());

        // --- Recuperar y verificar ---
        Repartidor encontrado = dao.buscarPorId(guardado.getId());
        assertNotNull(encontrado);

        // Datos básicos
        assertEquals("Luis Armando Vega", encontrado.getNombreCompleto());
        assertEquals(TipoTransporte.MOTO, encontrado.getTipoTransporte());
        assertEquals(EstadoRepartidor.PENDIENTE, encontrado.getEstado());

        // CuentaBancaria
        assertNotNull(encontrado.getCuentaBancaria());
        assertEquals("BBVA", encontrado.getCuentaBancaria().getBanco());
        assertEquals("1234567890", encontrado.getCuentaBancaria().getNumeroCuenta());

        // Documento
        assertNotNull(encontrado.getDocumento());
        assertArrayEquals(new byte[]{1, 2, 3}, encontrado.getDocumento().getIne());
        assertArrayEquals(new byte[]{4, 5, 6}, encontrado.getDocumento().getFotoPerfil());
    }
}
