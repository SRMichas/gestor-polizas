package com.soria.ApiPolizas.Model;

import lombok.Data;

@Data
public class Inventario extends GeneralParams{
    String SKU;
    String Nombre;
    int Cantidad;

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getSKU() {
        return SKU;
    }
}
