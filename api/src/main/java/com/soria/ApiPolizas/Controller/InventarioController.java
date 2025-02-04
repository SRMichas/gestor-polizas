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

    @GetMapping("/obtenerActivos")
    public ResponseEntity<ResponseAPI> ObtenerActivos(){
        ResponseAPI result = null;
        try
        {
            logger.info("Inicia ObtenerActivos");
            result = BuildResponse(inventarioServices.ObtenerActivos());
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

    @GetMapping("/ObtenerPorSKU/{sku}")
    public ResponseEntity<ResponseAPI> ObtenerPorSKU(@PathVariable String sku){
        ResponseAPI result = null;
        try
        {
            logger.info("Inicia ObtenerPorId");

            result = BuildResponse(inventarioServices.ObtenerPorSKU(sku));
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
    public ResponseEntity<ResponseAPI> ObtenerPaginado(@RequestBody Inventario inventario){
        ResponseAPI result = null;
        try
        {
            logger.info("Inicia ObtenerPaginado");

            result = BuildResponse(inventarioServices.ObtenerPaginado(inventario));
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
    public ResponseEntity<ResponseAPI> Registrar(@RequestBody Inventario inventario){
        ResponseAPI result = null;
        try
        {
            logger.info("Inicia Registrar");

            result = BuildResponse(inventarioServices.Registrar(inventario));
        }
        catch (Exception ex){
            result = BuildException(ex,"Ha ocurrido un error al obtener los empleados por ID");
            LogException(logger,"ERROR en Registrar", ex);
        }
        finally
        {
            logger.info("Termina Registrar");
        }

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/Actualizar")
    public ResponseEntity<ResponseAPI> Actualizar(@RequestBody Inventario inventario){
        ResponseAPI result = null;
        try
        {
            logger.info("Inicia Actualizar");
            result = BuildResponse(inventarioServices.Actualizar(inventario));
        }
        catch (Exception ex){
            result = BuildException("Ha ocurrido un error al actualiar el inventario");
            LogException(logger,"ERROR en Actualizar", ex);
        }
        finally
        {
            logger.info("Termina Actualizar");
        }

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/Eliminar")
    public ResponseEntity<ResponseAPI> Eliminar(@RequestBody Inventario inventario){
        ResponseAPI result = null;
        try
        {
            logger.info("Inicia Eliminar");

            result = BuildResponse(inventarioServices.Eliminar(inventario));
        }
        catch (Exception ex){
            result = BuildException("Ha ocurrido un error al eliminar el inventario");
            LogException(logger,"ERROR en Eliminar", ex);
        }
        finally
        {
            logger.info("Termina Eliminar");
        }

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }
}
