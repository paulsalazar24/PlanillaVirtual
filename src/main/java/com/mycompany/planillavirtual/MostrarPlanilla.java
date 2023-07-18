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
import java.text.DecimalFormat;

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

            System.out.print("Ingrese el número de cargas familiares: ");
            int numCargaFamiliar = scanner.nextInt();

            double sueldo = trabajadorEncontrado.getSueldo();
            double asignacionFamiliar = 103 * numCargaFamiliar;
            System.out.println("Asignación familiar: " + asignacionFamiliar);
            System.out.println("<INGRESOS>");
            System.out.println("Sueldo: " + sueldo);
            System.out.println("<====================>");
            double totalIngresos = sueldo + asignacionFamiliar;
            System.out.println("Total ingresos: " + totalIngresos);
            System.out.println("<DESCUENTOS>");
            System.out.println("Descuentos AFP (13%): " + formatDecimal(totalIngresos * 0.13));
            System.out.println("Descuentos seguro salud (9%): " + formatDecimal(totalIngresos * 0.09));
            System.out.println("Descuentos renta quinta (> 4950 desc. 8%): " + formatDecimal(totalIngresos > 4950 ? totalIngresos * 0.08 : 0));

            double totalDescuento = (totalIngresos * 0.13)
                    + (totalIngresos * 0.09)
                    + (totalIngresos > 4950 ? totalIngresos * 0.08 : 0);
            System.out.println("<====================>");
            System.out.println("Total descuento: " + formatDecimal(totalDescuento));
            double montoNeto = totalIngresos - totalDescuento;
            System.out.println("<LIQUIDO A COBRAR>");
            System.out.println("Monto neto: " + formatDecimal(montoNeto));
            System.out.println("<====================>");
            System.out.println("Fecha inicio: " + trabajadorEncontrado.getFechaInicio());
            System.out.println("Fecha retiro: " + trabajadorEncontrado.getFechaRetiro());
        } else {
            System.out.println("Trabajador no encontrado.");
        }
    }

    private static String formatDecimal(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return decimalFormat.format(value);
    }
}
