package com.gravata.netconsul.model;

/**
 * Created by bruno on 01/11/15.
 */
public enum SimNao {
    SIM("Sim"),
    NAO("Não");

    private String texto;

    SimNao(String texto) {
        this.texto=texto;
    }

    public String getTexto() {
        return texto;
    }
}
