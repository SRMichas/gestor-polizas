package com.soria.ApiPolizas.Controller;

import com.soria.ApiPolizas.Model.ResponseAPI;
import com.soria.ApiPolizas.Service.Interface.IEmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/empleado")
@CrossOrigin("*")
public class EmpleadoController extends ControllerBase{
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);


    @Autowired
    private IEmpleadoService empleadoServices;

    @GetMapping("/obtenerActivos")
    public ResponseEntity<ResponseAPI> ObtenerActivos(){
        ResponseAPI result = null;
        try
        {
            result = BuildResponse(empleadoServices.ObtenerActivos());
        }
        catch (Exception ex){
            result = BuildException("Ha ocurrido un error al obtener los empleados activos");
            LogException(logger, ex);
        }

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }
}
