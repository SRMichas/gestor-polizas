package com.soria.ApiPolizas.Controller;

import com.soria.ApiPolizas.Model.Empleado;
import com.soria.ApiPolizas.Model.ResponseAPI;
import com.soria.ApiPolizas.Service.Interface.IEmpleadoService;
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

    @GetMapping("/obtenerActivos")
    public ResponseEntity<ResponseAPI> ObtenerActivos(){
        ResponseAPI result = null;
        try
        {
        	logger.info("Inicia ObtenerActivos");
            result = BuildResponse(empleadoServices.ObtenerActivos());
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

    @GetMapping("/ObtenerPorId/{id}")
    public ResponseEntity<ResponseAPI> ObtenerPorId(@PathVariable int id){
        ResponseAPI result = null;
        try
        {
        	logger.info("Inicia ObtenerPorId");
            result = BuildResponse(empleadoServices.ObtenerPorId(id));
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

    @PostMapping("/ObtenerPaginado")
    public ResponseEntity<ResponseAPI> ObtenerPaginado(@RequestBody Empleado empleado){
        ResponseAPI result = null;
        try
        {
        	logger.info("Inicia ObtenerPaginado");
            result = BuildResponse(empleadoServices.ObtenerPaginado(empleado));
        }
        catch (Exception ex){
            result = BuildException("Ha ocurrido un error al obtener los empleados paginados");
            LogException(logger,"ERROR en ObtenerPaginado", ex);
        }
        finally
        {
            logger.info("Termina ObtenerPaginado");
        }

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/Registrar")
    public ResponseEntity<ResponseAPI> Registrar(@RequestBody Empleado empleado){
        ResponseAPI result = null;
        try
        {
        	logger.info("Inicia Registrar");
            result = BuildResponse(empleadoServices.Registrar(empleado));
        }
        catch (Exception ex){
            result = BuildException("Ha ocurrido un error al registrar el puesto");
            LogException(logger,"ERROR en Registrar", ex);
        }
        finally
        {
            logger.info("Termina Registrar");
        }

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/Actualizar")
    public ResponseEntity<ResponseAPI> Actualizar(@RequestBody Empleado empleado){
        ResponseAPI result = null;
        try
        {
        	logger.info("Inicia Actualizar");
            result = BuildResponse(empleadoServices.Actualizar(empleado));
        }
        catch (Exception ex){
            result = BuildException("Ha ocurrido un error al actualiar el puesto");
            LogException(logger,"ERROR en Actualizar", ex);
        }
        finally
        {
            logger.info("Termina Actualizar");
        }

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/Eliminar")
    public ResponseEntity<ResponseAPI> Eliminar(@RequestBody Empleado empleado){
        ResponseAPI result = null;
        try
        {
        	logger.info("Inicia Eliminar");
            result = BuildResponse(empleadoServices.Eliminar(empleado));
        }
        catch (Exception ex){
            result = BuildException("Ha ocurrido un error al eliminar el puesto");
            LogException(logger,"ERROR en Eliminar", ex);
        }
        finally
        {
            logger.info("Termina Eliminar");
        }

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }
}
