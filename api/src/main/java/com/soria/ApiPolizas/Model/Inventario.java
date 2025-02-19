package com.soria.ApiPolizas.Model;

import lombok.Data;

@Data
public class Inventario extends GeneralParams{
    String SKU;
    String Nombre;
    int Cantidad;
    int idPoliza;

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getSKU() {
        return SKU;
    }

    public int getIdPoliza() {
        return idPoliza;
    }

    public int getCantidad() {
        return Cantidad;
    }
}
