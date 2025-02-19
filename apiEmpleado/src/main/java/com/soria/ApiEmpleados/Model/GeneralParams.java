package com.soria.ApiEmpleados.Model;

import lombok.Data;

import java.util.Date;

@Data
public class GeneralParams {
    public Boolean Estatus;
    public String Mensaje;
    public int TotalPaginas;
    public String Busqueda;
    public int Pagina;
    public int Paginado;
    public Boolean Activo;
    public Date FechaRegistro;
}
