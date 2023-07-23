package com.mycompany.planillavirtual;

import static com.mycompany.planillavirtual.PlanillaVirtual.mostrarMenu;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AgregarTrabajador {

    public static void agregarTrabajador(List<Trabajador> trabajadores) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("AGREGAR TRABAJADOR");

        System.out.print("Ingrese el DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine();

        double sueldo = 0.0;
        boolean sueldoValido = false;
        while (!sueldoValido) {
            try {
                System.out.print("Ingrese el sueldo: ");
                sueldo = scanner.nextDouble();
                sueldoValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un valor válido para el sueldo.");
                scanner.nextLine();
            }
        }
        scanner.nextLine(); // Consumir la nueva línea en el búfer

        System.out.print("Ingrese la fecha de inicio: ");
        String fechaInicio = scanner.nextLine();
        System.out.print("Ingrese la fecha de retiro: ");
        String fechaRetiro = scanner.nextLine();

        Trabajador nuevoTrabajador = new Trabajador();
        nuevoTrabajador.setDni(dni);
        nuevoTrabajador.setNombre(nombre);
        nuevoTrabajador.setApellido(apellido);
        nuevoTrabajador.setSueldo(sueldo);
        nuevoTrabajador.setFechaInicio(fechaInicio);
        nuevoTrabajador.setFechaRetiro(fechaRetiro);

        mostrarListadoTrabajadores(trabajadores, nuevoTrabajador);

        // Preguntar al usuario si desea regresar al menú principal
        System.out.print("¿Desea regresar al menú principal? (Sí/No): ");
        String regresarMenu = scanner.nextLine();
        if (regresarMenu.equalsIgnoreCase("Sí")) {
            mostrarMenu(); // Llamamos al menú principal nuevamente
        }
    }

    public static void mostrarListadoTrabajadores(List<Trabajador> trabajadores, Trabajador trabajadorAgregado) {
        if (trabajadorAgregado != null) {
            System.out.println("TRABAJADOR AGREGADO:");
            DetallesTrabajadorHelper.imprimirEncabezado();
            String[] fila = {
                "1",
                trabajadorAgregado.getDni(),
                trabajadorAgregado.getNombre(),
                trabajadorAgregado.getApellido(),
                "S/." + Double.toString(trabajadorAgregado.getSueldo()),
                trabajadorAgregado.getFechaInicio()
            };
            DetallesTrabajadorHelper.imprimirFila(fila);
            DetallesTrabajadorHelper.imprimirLinea(6);
        }

        System.out.println("LISTADO COMPLETO DE TRABAJADORES:");
        DetallesTrabajadorHelper.imprimirEncabezado();
        int contador = 1;
        for (Trabajador trabajador : trabajadores) {
            if (trabajador != trabajadorAgregado) {
                String[] fila = {
                    String.valueOf(contador),
                    trabajador.getDni(),
                    trabajador.getNombre(),
                    trabajador.getApellido(),
                    "S/." + Double.toString(trabajador.getSueldo()),
                    trabajador.getFechaInicio()
                };
                DetallesTrabajadorHelper.imprimirFila(fila);
                contador++;
            }
        }
        DetallesTrabajadorHelper.imprimirLinea(6);
    }

}
