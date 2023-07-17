package com.mycompany.planillavirtual;

public class Usuario {

    private String nombre;
    private String contraseña;
    private boolean esAdmin;

    public Usuario(String nombre, String contraseña, boolean esAdmin) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.esAdmin = esAdmin;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public boolean esAdmin() {
        return esAdmin;
    }

}
