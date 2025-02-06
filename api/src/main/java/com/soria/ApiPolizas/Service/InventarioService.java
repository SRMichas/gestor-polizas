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

    
}
