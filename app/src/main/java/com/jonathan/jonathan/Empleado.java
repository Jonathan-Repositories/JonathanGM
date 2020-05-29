package com.jonathan.jonathan;

import java.io.Serializable;

public class Empleado implements Serializable {

    private String id;
    private String nombre;
    private String fecha;
    private String puesto;

    public Empleado(String id, String nombre, String fecha, String puesto) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.puesto = puesto;
    }

    public Empleado() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

}
