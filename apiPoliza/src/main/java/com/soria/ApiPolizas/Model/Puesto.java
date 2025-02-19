package com.soria.ApiPolizas.Model;

import lombok.Data;

@Data
public class Puesto extends GeneralParams{
    int IdPuesto;
    String Nombre;

    public void setIdPuesto(int idPuesto) {
        IdPuesto = idPuesto;
    }

    public int getIdPuesto() {
        return IdPuesto;
    }
}
