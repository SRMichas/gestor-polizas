package com.soria.ApiPolizas.Model;

import lombok.Data;
import java.util.Date;

@Data
public class Empleado extends GeneralParams{
    int Id;
    String Nombre;
    String Apellido;
    int PuestoID;
    String Puesto;
    Boolean Activo;
    Date FechaRegistro;

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }
}
