package com.soria.ApiPolizas.Model;

import lombok.Data;

@Data
public class Poliza extends GeneralParams{
    int IdPoliza;
    int IdEmpleado;
    String Empleado;
    String SKU;
    int Cantidad;
    String Inventario;

    public void setIdPoliza(int idPoliza) {
        IdPoliza = idPoliza;
    }

    public int getIdPoliza() {
        return IdPoliza;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public String getSKU() {
        return SKU;
    }

    public int getCantidad() {
        return Cantidad;
    }
}
