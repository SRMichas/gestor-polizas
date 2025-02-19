package com.soria.ApiPuestos.Model;

import lombok.Data;

@Data
public class ResponseAPI {
    private Meta Meta;
    private Object Data;

    public void setMeta(Meta meta) {
        Meta = meta;
    }

    public Meta getMeta() {
        return Meta;
    }

    public void setData(Object data) {
        Data = data;
    }

    public Object getData() {
        return Data;
    }
}
