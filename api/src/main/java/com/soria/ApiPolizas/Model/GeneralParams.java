package com.soria.ApiPolizas.Model;

import lombok.Data;

@Data
public class GeneralParams {
    public Boolean Estatus;
    public String Mensaje;
    public int TotalPaginas;
    public String Busqueda;
    public int Pagina;
    public int Paginado;
}
