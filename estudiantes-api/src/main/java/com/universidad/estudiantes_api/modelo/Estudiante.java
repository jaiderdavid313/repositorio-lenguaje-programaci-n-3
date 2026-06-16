package com.universidad.estudiantes_api.modelo;

public class Estudiante {

    private Long id;
    private String nombre;
    private String email;
    private String carrera;
    private double promedio;

    public Estudiante(Long id, String nombre, String email, String carrera, double promedio) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.carrera = carrera;
        this.promedio = promedio;
    }

    public Estudiante() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public double getPromedio() { return promedio; }
    public void setPromedio(double promedio) { this.promedio = promedio; }
}
