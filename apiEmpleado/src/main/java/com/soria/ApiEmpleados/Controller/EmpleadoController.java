package com.soria.ApiEmpleados.Controller;

import com.soria.ApiEmpleados.Model.Empleado;
import com.soria.ApiEmpleados.Model.ResponseAPI;
import com.soria.ApiEmpleados.Service.Interface.IEmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/empleado")
@CrossOrigin("*")
public class EmpleadoController extends ControllerBase{
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);


    @Autowired
    private IEmpleadoService empleadoServices;

    @GetMapping("")
    public ResponseEntity<ResponseAPI> obtenerActivos(){
        ResponseAPI result = null;
        try
        {
        	logger.info("Inicia ObtenerActivos");
            result = BuildResponse(empleadoServices.obtenerActivos());
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

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI> obtenerPorId(@PathVariable int id){
        ResponseAPI result = null;
        try
        {
        	logger.info("Inicia ObtenerPorId");
            result = BuildResponse(empleadoServices.obtenerPorId(id));
        }
        catch (Exception ex){
            result = BuildException("Ha ocurrido un error al obtener los empleados por ID");
            LogException(logger,"ERROR en ObtenerPorId", ex);
        }
        finally
        {
            logger.info("Termina ObtenerActivos");
        }

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<ResponseAPI> actualizar(@RequestBody Empleado empleado){
        ResponseAPI result = null;
        try
        {
        	logger.info("Inicia Actualizar");
            result = BuildResponse(empleadoServices.actualizar(empleado));
        }
        catch (Exception ex){
            result = BuildException("Ha ocurrido un error al actualiar el empleado");
            LogException(logger,"ERROR en Actualizar", ex);
        }
        finally
        {
            logger.info("Termina Actualizar");
        }

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

}
