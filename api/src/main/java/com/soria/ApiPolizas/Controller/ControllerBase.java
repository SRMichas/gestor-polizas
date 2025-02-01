package com.soria.ApiPolizas.Controller;

import com.soria.ApiPolizas.Model.Mensaje;
import com.soria.ApiPolizas.Model.Meta;
import com.soria.ApiPolizas.Model.ResponseAPI;
import org.slf4j.Logger;

public class ControllerBase {

    protected ResponseAPI BuildResponse(Object data){
        ResponseAPI response = new ResponseAPI();

        response.setMeta(new Meta("OK"));
        response.setData(data);

        return response;
    }

    protected ResponseAPI BuildException(String message){
        ResponseAPI response = new ResponseAPI();

        response.setMeta(new Meta("FAILURE"));
        response.setData(new Mensaje(message));

        return response;
    }

    protected void LogException(Logger logger, Exception ex){

        /*
        ex.toString()
        String msg = ex.getMessage(),
                trace = ex.getStackTrace().toString();
        Exception eIn = ex.InnerException;

        if (eIn != null)
        {
            msg += " ; " + eIn.Message;
            trace = eIn.StackTrace + "\n" + trace;
        }

        WebLog.Write($"[ERROR] {this.GetType().Name} Actualizar : {msg}\n{trace}");
        */

        String message = ex.toString();

        logger.error(message);
    }
}
