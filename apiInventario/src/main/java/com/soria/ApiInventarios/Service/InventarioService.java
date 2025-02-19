package com.soria.ApiInventarios.Service;

import com.soria.ApiInventarios.Model.Inventario;
import com.soria.ApiInventarios.Repository.Interface.IInventarioRepository;
import com.soria.ApiInventarios.Service.Interface.IInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService implements IInventarioService {

    @Autowired
    private IInventarioRepository inventarioRepository;

    @Override
    public List<Inventario> obtenerActivos() throws Exception {
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
    public Inventario ajusteInventario(Inventario inventario) throws Exception {
        List<Inventario> inventarios = List.of();
        Inventario inv = null;
        try
        {

            inventarios = inventarioRepository.ajusteInventario(inventario);
            if(!inventarios.isEmpty())
            {
                inv = inventarios.getFirst();

                boolean success = (inv != null) ? inv.Estatus : true;
                if( !success )
                    throw new Exception(inv.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }

        return inv;
    }

    @Override
    public Inventario restaurarInventario(Inventario inventario) throws Exception {
        List<Inventario> inventarios = List.of();
        Inventario inv = null;
        try
        {

            inventarios = inventarioRepository.restaurarInventario(inventario);
            if(!inventarios.isEmpty())
            {
                inv = inventarios.getFirst();

                boolean success = (inv != null) ? inv.Estatus : true;
                if( !success )
                    throw new Exception(inv.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }

        return inv;
    }

    
}
