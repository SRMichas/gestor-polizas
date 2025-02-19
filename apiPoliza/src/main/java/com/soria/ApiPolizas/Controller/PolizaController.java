package com.soria.ApiPolizas.Controller;

import com.soria.ApiPolizas.Model.Mensaje;
import com.soria.ApiPolizas.Model.Poliza;
import com.soria.ApiPolizas.Model.ResponseAPI;
import com.soria.ApiPolizas.Service.Interface.IPolizaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/poliza")
@CrossOrigin("*")
public class PolizaController extends ControllerBase{
    private static final Logger logger = LoggerFactory.getLogger(PolizaController.class);


    @Autowired
    private IPolizaService polizaServices;

    @PostMapping("/paginado")
    public ResponseEntity<ResponseAPI> obtenerPaginado(@RequestBody Poliza poliza){
        ResponseAPI result = null;
        try
        {
            logger.info("Inicia obtenerPaginado");

            result = BuildResponse(polizaServices.obtenerPaginado(poliza));
        }
        catch (Exception ex){
            result = BuildException("Ha ocurrido un error al obtener las polizas paginados");
            LogException(logger,"ERROR en ObtenerPaginado", ex);
        }
        finally
        {
            logger.info("Termina ObtenerPaginado");
        }

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ResponseAPI> registrar(@RequestBody Poliza poliza){
        ResponseAPI result = null;
        try
        {
            logger.info("Inicia Registrar");

            result = BuildResponse(polizaServices.registrar(poliza));
        }
        catch (Exception ex){
            result = BuildException(ex,"Ha ocurrido un error al registrar el poliza");
            LogException(logger,"ERROR en Registrar", ex);
        }
        finally
        {
            logger.info("Termina Registrar");
        }

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/cambiarempleado")
    public ResponseEntity<ResponseAPI> cambiarEmpleado(@RequestBody Poliza poliza){
        ResponseAPI result = null;
        try
        {
            logger.info("Inicia cambiarEmpleado");

            result = BuildResponse(polizaServices.cambiarEmpleado(poliza));
        }
        catch (Exception ex){
            result = BuildException(ex,"Ha ocurrido un error al cambiarEmpleado el poliza");
            LogException(logger,"ERROR en cambiarEmpleado", ex);
        }
        finally
        {
            logger.info("Termina cambiarEmpleado");
        }

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseAPI> eliminar(@RequestBody Poliza poliza){
        ResponseAPI result = null;
        try
        {
            logger.info("Inicia Eliminar");

            Poliza pol = polizaServices.eliminar(poliza);
            String msg = String.format("Se eliminó correctamente la poliza %d", pol.getIdPoliza());
            result = BuildResponse(new Mensaje(msg));
        }
        catch (Exception ex){
            result = BuildException(ex,"Ha ocurrido un error al eliminar el poliza");
            LogException(logger,"ERROR en Eliminar", ex);
        }
        finally
        {
            logger.info("Termina Eliminar");
        }

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }
}
