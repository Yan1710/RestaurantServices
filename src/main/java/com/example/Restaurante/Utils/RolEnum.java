package com.example.Restaurante.Utils;

public enum RolEnum {
    RESTAURANTE("Restaurante"),
    CLIENTE("cliente"),
    DOMICILIARIO("Domiciliario"),
    ADMINISTRADOR("administrador");

    private final String valor;

    RolEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
