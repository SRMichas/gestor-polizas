package com.soria.ApiPolizas.Model;

import lombok.Data;

@Data
public class Poliza extends GeneralParams{
    int Id;
    int EmpleadoID;
    String Empleado;
    String SKU;
    int Cantidad;

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }
}
