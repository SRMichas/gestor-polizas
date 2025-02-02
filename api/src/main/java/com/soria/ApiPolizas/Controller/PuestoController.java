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

    @GetMapping("/ObtenerPorId/{id}")
    public ResponseEntity<ResponseAPI> ObtenerPorId(@PathVariable int id){
        ResponseAPI result = null;
        try
        {
            logger.info("Inicia ObtenerPorId");

            result = BuildResponse(puestoServices.ObtenerPorId(id));
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
    public ResponseEntity<ResponseAPI> ObtenerPaginado(@RequestBody Puesto puesto){
        ResponseAPI result = null;
        try
        {
            logger.info("Inicia ObtenerPaginado");

            result = BuildResponse(puestoServices.ObtenerPaginado(puesto));
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
    public ResponseEntity<ResponseAPI> Registrar(@RequestBody Puesto puesto){
        ResponseAPI result = null;
        try
        {
            logger.info("Inicia Registrar");

            result = BuildResponse(puestoServices.Registrar(puesto));
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
    public ResponseEntity<ResponseAPI> Actualizar(@RequestBody Puesto puesto){
        ResponseAPI result = null;
        try
        {
            logger.info("Inicia Actualizar");
            result = BuildResponse(puestoServices.Actualizar(puesto));
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
    public ResponseEntity<ResponseAPI> Eliminar(@RequestBody Puesto puesto){
        ResponseAPI result = null;
        try
        {
            logger.info("Inicia Eliminar");

            result = BuildResponse(puestoServices.Eliminar(puesto));
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
