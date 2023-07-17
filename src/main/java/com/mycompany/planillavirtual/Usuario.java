package com.mycompany.planillavirtual;

public class Usuario {

    private String nombre;
    private String password;
    private boolean esAdmin;

    public Usuario(String nombre, String password, boolean esAdmin) {
        this.nombre = nombre;
        this.password = password;
        this.esAdmin = esAdmin;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public boolean esAdmin() {
        return esAdmin;
    }
    
    public static Usuario crearUsuario(String nombreUsuario, String password, boolean esAdmin) {
        return new Usuario(nombreUsuario, password, esAdmin);
    }

}
