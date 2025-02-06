package com.soria.ApiPolizas.Controller;

import com.soria.ApiPolizas.Model.Puesto;
import com.soria.ApiPolizas.Model.ResponseAPI;
import com.soria.ApiPolizas.Service.Interface.IEmpleadoService;
import com.soria.ApiPolizas.Service.Interface.IPuestoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/puesto")
@CrossOrigin("*")
public class PuestoController extends ControllerBase{
    private static final Logger logger = LoggerFactory.getLogger(PuestoController.class);


    @Autowired
    private IPuestoService puestoServices;

    @GetMapping("/obtenerActivos")
    public ResponseEntity<ResponseAPI> ObtenerActivos(){
        ResponseAPI result = null;
        try
        {
            logger.info("Inicia ObtenerActivos");
            result = BuildResponse(puestoServices.ObtenerActivos());
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
