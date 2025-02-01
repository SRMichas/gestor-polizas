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
}
