package com.prueba.microservicios.app.usuarios.models.resttemplate;

public class Camisa {

    private String tamanio;
    private String color;
    private String tipo;

    public Camisa() {
    }

    public Camisa(String tamanio, String color, String tipo) {
        this.tamanio = tamanio;
        this.color = color;
        this.tipo = tipo;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

