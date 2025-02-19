package com.soria.ApiEmpleados.Model;

import lombok.Data;

@Data
public class Mensaje {
    private String IDMensaje;

    public Mensaje(){}

    public Mensaje(String _mensaje){
        this.IDMensaje = _mensaje;
    }
}
