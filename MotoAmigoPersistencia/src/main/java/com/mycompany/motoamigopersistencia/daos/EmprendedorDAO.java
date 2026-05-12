package com.mycompany.motoamigopersistencia.daos;

import com.mycompany.motoamigopersistencia.interfaces.IEmprendedorDAO;
import com.mycompany.motoamigodominio.entities.Emprendedor;
import com.mycompany.motoamigodominio.enums.EstadoEmprendedor;
import java.util.ArrayList;
import java.util.List;

public class EmprendedorDAO implements IEmprendedorDAO {
    
    // Lista estática que simulará nuestra tabla en BD
    private static List<Emprendedor> baseDatosEmprendedores = new ArrayList<>();

    // Bloque estático: Precargamos 3 emprendedors mock
    static {
        //Emprendedor 1
        Emprendedor e1 = new Emprendedor();
        e1.setId("EMP-1");
        e1.setNombre("Ana");
        e1.setApellidop("García");
        e1.setApellidoM("López");
        e1.setCorreoElectronico("ana.postres@email.com");
        e1.setTelefono("5551112233");
        e1.setDireccion("Calle Dulce 123, Centro");
        e1.setContrasenia("passAna123");
        e1.setDocumentoIdentificacionINE(new byte[]{1, 2, 3});
        e1.setCuentaBancaria("012345678901234567");
        e1.setNombreNegocio("Postres Doña Ana");
        e1.setDescripcionNegocio("Venta de pasteles y galletas caseras artesanales.");
        e1.setEstado(EstadoEmprendedor.ACTIVO);
        baseDatosEmprendedores.add(e1);

        //Emprendedor 2
        Emprendedor e2 = new Emprendedor();
        e2.setId("EMP-2");
        e2.setNombre("Roberto");
        e2.setApellidop("Martínez");
        e2.setApellidoM("Cruz");
        e2.setCorreoElectronico("roberto.tech@email.com");
        e2.setTelefono("5554445566");
        e2.setDireccion("Av. Tecnológica 456, Norte");
        e2.setContrasenia("techRob456");
        e2.setDocumentoIdentificacionINE(new byte[]{1, 2, 3});
        e2.setCuentaBancaria("987654321098765432");
        e2.setNombreNegocio("Tech Fix & Accesorios");
        e2.setDescripcionNegocio("Reparación de celulares y venta de fundas.");
        e2.setEstado(EstadoEmprendedor.ACTIVO);
        baseDatosEmprendedores.add(e2);

        //Emprendedor 3
        Emprendedor e3 = new Emprendedor();
        e3.setId("EMP-3");
        e3.setNombre("Lucía");
        e3.setApellidop("Fernández");
        e3.setApellidoM("Gómez");
        e3.setCorreoElectronico("lucia.ropa@email.com");
        e3.setTelefono("5557778899");
        e3.setDireccion("Plaza Moda Sur, Local 12");
        e3.setContrasenia("modaLu789");
        e3.setDocumentoIdentificacionINE(new byte[]{1, 2, 3});
        e3.setCuentaBancaria("456123789012345678");
        e3.setNombreNegocio("Boutique Vanguardia");
        e3.setDescripcionNegocio("Venta de ropa para dama de temporada.");
        e3.setEstado(EstadoEmprendedor.ACTIVO);
        baseDatosEmprendedores.add(e3);
        
        System.out.println("Base de datos inicializada con 3 EMPRENDEDORES.");
    }

    @Override
    public boolean existeCorreo(String correo) throws PersistenciaException {
        for (Emprendedor e : baseDatosEmprendedores) {
            if (e.getCorreoElectronico().equalsIgnoreCase(correo)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void guardarEmprendedor(Emprendedor emprendedor) throws PersistenciaException {
        // Autoincremento de idd
        if (emprendedor.getId() == null || emprendedor.getId().isEmpty()) {
            emprendedor.setId("EMP-" + (baseDatosEmprendedores.size() + 1));
        }
        
        baseDatosEmprendedores.add(emprendedor);
        
        System.out.println("Emprendedor " + emprendedor.getNombreNegocio() + " guardado exitosamente. Total registros: " + baseDatosEmprendedores.size());
    }
    @Override
    public Emprendedor obtenerEmprendedorSesion() throws PersistenciaException {
        // Simulamos que el Emprendedor 1 fue el que inició sesión
        if (!baseDatosEmprendedores.isEmpty()) {
            return baseDatosEmprendedores.get(0); 
        }
        return null;
    }
}