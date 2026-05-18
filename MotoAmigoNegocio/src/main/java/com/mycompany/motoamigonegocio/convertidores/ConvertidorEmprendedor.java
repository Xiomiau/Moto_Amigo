package com.mycompany.motoamigonegocio.convertidores;

import com.mycompany.motoamigodto.emprendedor.EmprendedorDTO;
import com.mycompany.motoamigodto.emprendedor.EstadoEmprendedorDTO;
import entities.CuentaBancaria;
import entities.Documento;
import entities.Emprendedor;
import enums.EstadoEmprendedor;



public class ConvertidorEmprendedor {

    public Emprendedor mapearDtoAEntidad(EmprendedorDTO dto) {
        Emprendedor entidad = new Emprendedor();
        Documento documento = new Documento();
        CuentaBancaria cuentaBancaria = new CuentaBancaria();
        entidad.setId(dto.id);
        entidad.setCorreoElectronico(dto.correoElectronico);
        entidad.setTelefono(dto.telefono);
        entidad.setDireccion(dto.direccion);
        entidad.setContrasenia(dto.contrasenia);
        entidad.setDocumento(documento);
        entidad.setCuentaBancaria(cuentaBancaria);
        entidad.setNombreNegocio(dto.nombreNegocio);
        entidad.setDescripcionNegocio(dto.descripcionNegocio);
        
        if (dto.estado != null) {
             entidad.setEstado(dto.estado == EstadoEmprendedorDTO.ACTIVO ? EstadoEmprendedor.ACTIVO : EstadoEmprendedor.INACTIVO);
        }
        return entidad;
    }
    
    public EmprendedorDTO mapearEntidadADTO(Emprendedor entidad) {
        if (entidad == null) return null;
        EmprendedorDTO dto = new EmprendedorDTO();
        dto.id = entidad.getId();
        dto.nombre = entidad.getNombrecompleto();
        dto.nombreNegocio = entidad.getNombreNegocio();
        // Puedes agregar más campos si los necesitas mostrar en la UI
        return dto;
    }
}
