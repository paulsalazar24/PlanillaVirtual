/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.planillavirtual;

/**
 *
 * @author LENOVO
 */
import static com.mycompany.planillavirtual.PlanillaVirtual.buscarTrabajadorPorDni;
import java.util.Scanner;

public class MostrarPlanilla {
    private String dni;
    private String nombre;
    private String apellido;
    private double sueldo;
    private String fechaInicio;
    private String fechaRetiro;

    public MostrarPlanilla(String dni, String nombre, String apellido, double sueldo, String fechaInicio, String fechaRetiro) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sueldo = sueldo;
        this.fechaInicio = fechaInicio;
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


  public static void mostrarPlanilla() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el DNI del trabajador: ");
        String dni = scanner.nextLine();

        Trabajador trabajadorEncontrado = buscarTrabajadorPorDni(dni);

        if (trabajadorEncontrado != null) {
            System.out.println("<====================>");
            System.out.println("BOLETA DE PAGO");
            System.out.println("<====================>");
            System.out.println("<DATOS DEL TRABAJADOR>");
            System.out.println("Nombre: " + trabajadorEncontrado.getNombre() + " " + trabajadorEncontrado.getApellido());
            System.out.println("DNI: " + trabajadorEncontrado.getDni());
            System.out.println("<INGRESOS>");
            System.out.println("Sueldo: " + trabajadorEncontrado.getSueldo());
            System.out.println("Asignación familiar: 103");
            System.out.println("<DESCUENTOS>");
            System.out.println("Descuentos AFP (13%): " + (trabajadorEncontrado.getSueldo() * 0.13));
            System.out.println("Descuentos seguro salud (9%): " + (trabajadorEncontrado.getSueldo() * 0.09));
            System.out.println("Descuentos renta quinta (> 4950 desc. 8%): " + (trabajadorEncontrado.getSueldo() > 4950 ? trabajadorEncontrado.getSueldo() * 0.08 : 0));
            
            double totalDescuento = (trabajadorEncontrado.getSueldo() * 0.13)
                    + (trabajadorEncontrado.getSueldo() * 0.09)
                    + (trabajadorEncontrado.getSueldo() > 4950 ? trabajadorEncontrado.getSueldo() * 0.08 : 0);
            System.out.println("<====================>");
            System.out.println("Total descuento: " + totalDescuento);
            double montoNeto = trabajadorEncontrado.getSueldo() - totalDescuento;
            System.out.println("<LIQUIDO A COBRAR>");
            System.out.println("Monto neto: " + montoNeto);
            System.out.println("<====================>");
            System.out.println("Fecha inicio: " + trabajadorEncontrado.getFechaInicio());
            System.out.println("Fecha retiro: " + trabajadorEncontrado.getFechaRetiro());
        } else {
            System.out.println("Trabajador no encontrado.");
        }
    }
}