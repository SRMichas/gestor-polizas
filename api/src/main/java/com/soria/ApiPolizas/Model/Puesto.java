package com.soria.ApiPolizas.Model;

import lombok.Data;

@Data
public class Puesto extends GeneralParams{
    int Id;
    String Nombre;

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }
}
