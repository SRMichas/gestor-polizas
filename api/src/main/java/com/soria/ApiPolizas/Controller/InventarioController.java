package com.soria.ApiPolizas.Controller;

import com.soria.ApiPolizas.Model.Inventario;
import com.soria.ApiPolizas.Model.ResponseAPI;
import com.soria.ApiPolizas.Service.Interface.IInventarioService;
import com.soria.ApiPolizas.Service.Interface.IPuestoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/inventario")
@CrossOrigin("*")
public class InventarioController extends ControllerBase{
    private static final Logger logger = LoggerFactory.getLogger(InventarioController.class);


    @Autowired
    private IInventarioService inventarioServices;

    @GetMapping("")
    public ResponseEntity<ResponseAPI> obtenerActivos(){
        ResponseAPI result = null;
        try
        {
            logger.info("Inicia ObtenerActivos");
            result = BuildResponse(inventarioServices.obtenerActivos());
        }
        catch (Exception ex){
            result = BuildException("Ha ocurrido un error al obtener los empleados activos");
            LogException(logger,"ERROR en ObtenerActivos", ex);
        }
        finally
        {
            logger.info("Termina ObtenerActivos");
        }

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }
}
