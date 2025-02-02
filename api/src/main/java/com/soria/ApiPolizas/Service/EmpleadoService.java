package com.soria.ApiPolizas.Service;

import com.soria.ApiPolizas.Model.Empleado;
import com.soria.ApiPolizas.Model.ResponseAPI;
import com.soria.ApiPolizas.Repository.Interface.IEmpleadoRepository;
import com.soria.ApiPolizas.Service.Interface.IEmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmpleadoService extends ServiceBase implements IEmpleadoService {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoService.class);
    @Autowired
    private IEmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> ObtenerActivos() throws Exception {
        List<Empleado> empleados = List.of();
        try
        {
            empleados = empleadoRepository.obtenerActivos();
            if(!empleados.isEmpty())
            {
                Empleado empleado = empleados.getFirst();

                boolean success = (empleado != null) ? empleado.Estatus : true;
                if( !success )
                    throw new Exception(empleado.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }
        

        return empleados;
    }

    @Override
    public List<Empleado> ObtenerPorId(int empleadoID) throws Exception {
        List<Empleado> empleados = List.of();
        try
        {
            Empleado empleado = new Empleado();
            empleado.setId(empleadoID);

            empleados = empleadoRepository.ObtenerPorId(empleado);
            if(!empleados.isEmpty())
            {
                empleado = empleados.getFirst();

                boolean success = (empleado != null) ? empleado.Estatus : true;
                if( !success )
                    throw new Exception(empleado.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }

        return empleados;
    }

    @Override
    public List<Empleado> ObtenerPaginado(Empleado empleado) throws Exception {
        List<Empleado> empleados = List.of();
        try
        {

            empleados = empleadoRepository.ObtenerPaginado(empleado);
            if(!empleados.isEmpty())
            {
                Empleado emp = empleados.getFirst();

                boolean success = (emp != null) ? emp.Estatus : true;
                if( !success )
                    throw new Exception(emp.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }

        return empleados;
    }

    @Override
    public Empleado Registrar(Empleado empleado) throws Exception {
        Empleado emp = null;
        try
        {
            emp = empleadoRepository.Registrar(empleado);
            if(!emp.Estatus)
            {
                throw new Exception(empleado.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }
        

        return emp;
    }

    @Override
    public Empleado Actualizar(Empleado empleado) throws Exception {
        Empleado emp = null;
        try
        {

            emp = empleadoRepository.Actualizar(empleado);
            if(!emp.Estatus)
            {
                throw new Exception(empleado.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }

        return emp;
    }

    @Override
    public Empleado Eliminar(Empleado empleado) throws Exception {
        Empleado emp = null;
        try
        {
            emp = empleadoRepository.Eliminar(empleado);
            if(!emp.Estatus)
            {
                throw new Exception(empleado.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }

        return emp;
    }
}
