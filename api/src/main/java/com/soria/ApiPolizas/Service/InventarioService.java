package com.soria.ApiPolizas.Service;

import com.soria.ApiPolizas.Model.Inventario;
import com.soria.ApiPolizas.Repository.Interface.IInventarioRepository;
import com.soria.ApiPolizas.Repository.Interface.IPuestoRepository;
import com.soria.ApiPolizas.Service.Interface.IInventarioService;
import com.soria.ApiPolizas.Service.Interface.IPuestoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService extends ServiceBase implements IInventarioService {

    private static final Logger logger = LoggerFactory.getLogger(InventarioService.class);
    @Autowired
    private IInventarioRepository inventarioRepository;

    @Override
    public List<Inventario> ObtenerActivos() throws Exception {
        List<Inventario> inventarios = List.of();
        try
        {
            
            inventarios = inventarioRepository.obtenerActivos();
            if(!inventarios.isEmpty())
            {
                Inventario inventario = inventarios.getFirst();

                boolean success = (inventario != null) ? inventario.Estatus : true;
                if( !success )
                    throw new Exception(inventario.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }

        return inventarios;
    }

    @Override
    public List<Inventario> ObtenerPorSKU(String sku) throws Exception {
        List<Inventario> inventarios = List.of();
        try
        {
            Inventario inventario = new Inventario();
            inventario.setSKU(sku);

            inventarios = inventarioRepository.ObtenerPorSKU(inventario);
            if(!inventarios.isEmpty())
            {
                inventario = inventarios.getFirst();

                boolean success = (inventario != null) ? inventario.Estatus : true;
                if( !success )
                    throw new Exception(inventario.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }

        return inventarios;
    }

    @Override
    public List<Inventario> ObtenerPaginado(Inventario inventario) throws Exception {
        List<Inventario> inventarios = List.of();
        try
        {
            inventarios = inventarioRepository.ObtenerPaginado(inventario);
            if(!inventarios.isEmpty())
            {
                Inventario inv = inventarios.getFirst();

                boolean success = (inv != null) ? inv.Estatus : true;
                if( !success )
                    throw new Exception(inv.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }
        

        return inventarios;
    }

    @Override
    public Inventario Registrar(Inventario inventario) throws Exception {
        Inventario inv = null;
        try
        {
            inv = inventarioRepository.Registrar(inventario);
            if(!inv.Estatus)
            {
                throw new Exception(inv.Mensaje);
            }
        }
        catch (Exception ex){
            logger.error("ERROR en Registrar",ex);
            throw ex;
        }
        

        return inv;
    }

    @Override
    public Inventario Actualizar(Inventario inventario) throws Exception {
        Inventario inv = null;
        try
        {
            inv = inventarioRepository.Actualizar(inventario);
            if(!inv.Estatus)
            {
                throw new Exception(inv.Mensaje);
            }
        }
        catch (Exception ex){
            logger.error("ERROR en Actualizar",ex);
            throw ex;
        }
        

        return inv;
    }

    @Override
    public Inventario Eliminar(Inventario inventario) throws Exception {
        Inventario inv = null;
        try
        {
            inv = inventarioRepository.Eliminar(inventario);
            if(!inv.Estatus)
            {
                throw new Exception(inv.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }

        return inv;
    }
}
