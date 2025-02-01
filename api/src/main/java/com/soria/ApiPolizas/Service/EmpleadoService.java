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
            logger.info("Inicia ObtenerActivos");

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
            logger.error("ERROR en ObtenerActivos",ex);
            throw ex;
        }
        finally
        {
            logger.info("Termina ObtenerActivos");
        }

        return empleados;
    }

    @Override
    public List<Empleado> ObtenerPorId(int empleadoID) throws Exception {
        List<Empleado> empleados = List.of();
        try
        {
            logger.info("Inicia ObtenerPorId");

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
            logger.error("ERROR en ObtenerPorId",ex);
            throw ex;
        }
        finally
        {
            logger.info("Termina ObtenerPorId");
        }

        return empleados;
    }

    @Override
    public List<Empleado> ObtenerPaginado(Empleado empleado) throws Exception {
        List<Empleado> empleados = List.of();
        try
        {
            logger.info("Inicia ObtenerPaginado");

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
            logger.error("ERROR en ObtenerPaginado",ex);
            throw ex;
        }
        finally
        {
            logger.info("Termina ObtenerPaginado");
        }

        return empleados;
    }

    @Override
    public Empleado Registrar(Empleado empleado) throws Exception {
        Empleado emp = null;
        try
        {
            logger.info("Inicia Registrar");

            emp = empleadoRepository.Registrar(empleado);
            if(!emp.Estatus)
            {
                throw new Exception(empleado.Mensaje);
            }
        }
        catch (Exception ex){
            logger.error("ERROR en Registrar",ex);
            throw ex;
        }
        finally
        {
            logger.info("Termina Registrar");
        }

        return emp;
    }

    @Override
    public Empleado Actualizar(Empleado empleado) throws Exception {
        Empleado emp = null;
        try
        {
            logger.info("Inicia Actualizar");

            emp = empleadoRepository.Actualizar(empleado);
            if(!emp.Estatus)
            {
                throw new Exception(empleado.Mensaje);
            }
        }
        catch (Exception ex){
            logger.error("ERROR en Actualizar",ex);
            throw ex;
        }
        finally
        {
            logger.info("Termina Actualizar");
        }

        return emp;
    }

    @Override
    public Empleado Eliminar(Empleado empleado) throws Exception {
        Empleado emp = null;
        try
        {
            logger.info("Inicia Eliminar");

            emp = empleadoRepository.Eliminar(empleado);
            if(!emp.Estatus)
            {
                throw new Exception(empleado.Mensaje);
            }
        }
        catch (Exception ex){
            logger.error("ERROR en Eliminar",ex);
            throw ex;
        }
        finally
        {
            logger.info("Termina Eliminar");
        }

        return emp;
    }
}
