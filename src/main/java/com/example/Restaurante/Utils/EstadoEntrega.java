package com.example.Restaurante.Utils;

public enum EstadoEntrega {
    PENDIENTE("Pendiente"),
    PREPARANDO("PREPARANDO"),
    EN_CAMINO("En Camino"),
    ENTREGADO("Entregado"),
    ASIGNADO("Asignado");

    private final String valor;

    EstadoEntrega(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
