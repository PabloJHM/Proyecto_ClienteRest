package com.example.dam.proyecto_clienterest.Pojo;


public class Profesor {
    private int idProfesor;
    private String nombre,apellidos,departamento;

    public Profesor(){}

    public Profesor(int id, String nombre, String apellidos, String departamento) {
        this.idProfesor = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.departamento = departamento;
    }

    public int getId() {
        return idProfesor;
    }

    public void setId(int id) {
        this.idProfesor = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
