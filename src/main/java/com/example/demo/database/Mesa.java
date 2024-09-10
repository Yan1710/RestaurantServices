package com.example.demo.database;

public class Mesa {

    private int id;
    private String nombre;
    private int puestos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPuestos() {
        return puestos;
    }

    public void setPuestos(int puestos) {
        this.puestos = puestos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Mesa{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", puestos=" + puestos +
                '}';
    }
}
