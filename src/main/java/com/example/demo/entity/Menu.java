package com.example.demo.entity;

import java.util.ArrayList;

public class Menu {

    private int id;
    private String nambreComida;
    private ArrayList<Contenido>contenidos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNambreComida() {
        return nambreComida;
    }

    public void setNambreComida(String nambreComida) {
        this.nambreComida = nambreComida;
    }

    public ArrayList<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(ArrayList<Contenido> contenidos) {
        this.contenidos = contenidos;
    }
}
