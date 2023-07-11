
package com.mycompany.planillavirtual;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Trabajador {
    private String dni;
    private String nombre;
    private String apellido;
    private double sueldo;
    private String fechaInicio;
    private String fechaRetiro;

    // Constructor, getters y setters

    // Método para cargar los trabajadores desde el archivo JSON
    public static List<Trabajador> cargarTrabajadoresDesdeJSON(String rutaArchivo) {
        List<Trabajador> trabajadores = new ArrayList<>();

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(rutaArchivo)) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            for (Object obj : jsonArray) {
                JSONObject jsonObj = (JSONObject) obj;
                String dni = (String) jsonObj.get("dni");
                String nombre = (String) jsonObj.get("nombre");
                String apellido = (String) jsonObj.get("apellido");
                double sueldo = (double) jsonObj.get("sueldo");
                String fechaInicio = (String) jsonObj.get("fechaInicio");
                String fechaRetiro = (String) jsonObj.get("fechaRetiro");

                Trabajador trabajador = new Trabajador();
                trabajador.setDni(dni);
                trabajador.setNombre(nombre);
                trabajador.setApellido(apellido);
                trabajador.setSueldo(sueldo);
                trabajador.setFechaInicio(fechaInicio);
                trabajador.setFechaRetiro(fechaRetiro);

                trabajadores.add(trabajador);
            }
        } catch (IOException | ParseException e) {
        }

        return trabajadores;
    }
    // Otros métodos de la clase Trabajador

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaRetiro(String fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public double getSueldo() {
        return sueldo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaRetiro() {
        return fechaRetiro;
    }

    


 
}

