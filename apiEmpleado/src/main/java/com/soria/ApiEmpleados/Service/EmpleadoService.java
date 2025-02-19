package com.soria.ApiEmpleados.Service;

import com.soria.ApiEmpleados.Model.Empleado;
import com.soria.ApiEmpleados.Repository.Interface.IEmpleadoRepository;
import com.soria.ApiEmpleados.Service.Interface.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmpleadoService extends ServiceBase implements IEmpleadoService {

    @Autowired
    private IEmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> obtenerActivos() throws Exception {
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
    public List<Empleado> obtenerPorId(int empleadoID) throws Exception {
        List<Empleado> empleados = List.of();
        try
        {

            empleados = empleadoRepository.ObtenerPorId(empleadoID);
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
    public Empleado actualizar(Empleado empleado) throws Exception {
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

}
