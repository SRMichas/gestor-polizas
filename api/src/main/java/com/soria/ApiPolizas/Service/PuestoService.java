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
            logger.info("Inicia ObtenerActivos");

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
            logger.error("ERROR en ObtenerActivos",ex);
            throw ex;
        }
        finally
        {
            logger.info("Termina ObtenerActivos");
        }

        return puestos;
    }

    @Override
    public List<Puesto> ObtenerPorId(int empleadoID) throws Exception {
        List<Puesto> puestos = List.of();
        try
        {
            logger.info("Inicia ObtenerPorId");

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
            logger.error("ERROR en ObtenerPorId",ex);
            throw ex;
        }
        finally
        {
            logger.info("Termina ObtenerPorId");
        }

        return puestos;
    }

    @Override
    public List<Puesto> ObtenerPaginado(Puesto puesto) throws Exception {
        List<Puesto> puestos = List.of();
        try
        {
            logger.info("Inicia ObtenerPaginado");

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
            logger.error("ERROR en ObtenerPaginado",ex);
            throw ex;
        }
        finally
        {
            logger.info("Termina ObtenerPaginado");
        }

        return puestos;
    }

    @Override
    public Puesto Registrar(Puesto puesto) throws Exception {
        Puesto emp = null;
        try
        {
            logger.info("Inicia Registrar");

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
        finally
        {
            logger.info("Termina Registrar");
        }

        return emp;
    }

    @Override
    public Puesto Actualizar(Puesto puesto) throws Exception {
        Puesto emp = null;
        try
        {
            logger.info("Inicia Actualizar");

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
        finally
        {
            logger.info("Termina Actualizar");
        }

        return emp;
    }

    @Override
    public Puesto Eliminar(Puesto puesto) throws Exception {
        Puesto emp = null;
        try
        {
            logger.info("Inicia Eliminar");

            emp = puestoRepository.Eliminar(puesto);
            if(!emp.Estatus)
            {
                throw new Exception(puesto.Mensaje);
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
