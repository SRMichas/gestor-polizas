package com.soria.ApiPolizas.Model;

import lombok.Data;

@Data
public class Empleado extends GeneralParams{
    int IdEmpleado;
    String Nombre;
    String Apellido;
    int IdPuesto;
    String Puesto;

    public void setIdEmpleado(int idEmpleado) {
        IdEmpleado = idEmpleado;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }
}
