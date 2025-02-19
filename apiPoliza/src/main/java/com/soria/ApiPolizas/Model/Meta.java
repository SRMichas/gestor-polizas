package com.soria.ApiPolizas.Model;

import lombok.Data;

@Data
public class Meta {
    private String Status;

    public Meta(){}
    public Meta(String _meta){
        this.Status = _meta;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getStatus() {
        return Status;
    }
}
