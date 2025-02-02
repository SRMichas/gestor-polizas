package com.soria.ApiPolizas.Service;

import com.soria.ApiPolizas.Model.Puesto;
import com.soria.ApiPolizas.Repository.Interface.IPuestoRepository;
import com.soria.ApiPolizas.Service.Interface.IPuestoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PuestoService extends ServiceBase implements IPuestoService {

    private static final Logger logger = LoggerFactory.getLogger(PuestoService.class);
    @Autowired
    private IPuestoRepository puestoRepository;

    @Override
    public List<Puesto> ObtenerActivos() throws Exception {
        List<Puesto> puestos = List.of();
        try
        {
            
            puestos = puestoRepository.obtenerActivos();
            if(!puestos.isEmpty())
            {
                Puesto puesto = puestos.getFirst();

                boolean success = (puesto != null) ? puesto.Estatus : true;
                if( !success )
                    throw new Exception(puesto.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }

        return puestos;
    }

    @Override
    public List<Puesto> ObtenerPorId(int empleadoID) throws Exception {
        List<Puesto> puestos = List.of();
        try
        {
            Puesto puesto = new Puesto();
            puesto.setId(empleadoID);

            puestos = puestoRepository.ObtenerPorId(puesto);
            if(!puestos.isEmpty())
            {
                puesto = puestos.getFirst();

                boolean success = (puesto != null) ? puesto.Estatus : true;
                if( !success )
                    throw new Exception(puesto.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }

        return puestos;
    }

    @Override
    public List<Puesto> ObtenerPaginado(Puesto puesto) throws Exception {
        List<Puesto> puestos = List.of();
        try
        {
            puestos = puestoRepository.ObtenerPaginado(puesto);
            if(!puestos.isEmpty())
            {
                Puesto emp = puestos.getFirst();

                boolean success = (emp != null) ? emp.Estatus : true;
                if( !success )
                    throw new Exception(emp.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }
        

        return puestos;
    }

    @Override
    public Puesto Registrar(Puesto puesto) throws Exception {
        Puesto emp = null;
        try
        {
            emp = puestoRepository.Registrar(puesto);
            if(!emp.Estatus)
            {
                throw new Exception(puesto.Mensaje);
            }
        }
        catch (Exception ex){
            logger.error("ERROR en Registrar",ex);
            throw ex;
        }
        

        return emp;
    }

    @Override
    public Puesto Actualizar(Puesto puesto) throws Exception {
        Puesto emp = null;
        try
        {
            emp = puestoRepository.Actualizar(puesto);
            if(!emp.Estatus)
            {
                throw new Exception(puesto.Mensaje);
            }
        }
        catch (Exception ex){
            logger.error("ERROR en Actualizar",ex);
            throw ex;
        }
        

        return emp;
    }

    @Override
    public Puesto Eliminar(Puesto puesto) throws Exception {
        Puesto emp = null;
        try
        {
            emp = puestoRepository.Eliminar(puesto);
            if(!emp.Estatus)
            {
                throw new Exception(puesto.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }

        return emp;
    }
}
