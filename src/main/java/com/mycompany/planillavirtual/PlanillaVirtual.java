package com.mycompany.planillavirtual;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class PlanillaVirtual {
    private static List<Trabajador> trabajadores;
    private static final String RUTA_ARCHIVO_JSON = "src/main/java/datos/trabajadores.json";

    public static void main(String[] args) {
        trabajadores = Trabajador.cargarTrabajadoresDesdeJSON(RUTA_ARCHIVO_JSON);
        mostrarMenu();
    }

    public static void mostrarMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcion = 0;
            
            while (opcion != 4) {
                System.out.println("MENU");
                System.out.println("1. Planilla");
                System.out.println("2. Agregar trabajador");
                System.out.println("3. Búsqueda de trabajador");
                System.out.println("4. Trabajadores");
                System.out.print("Ingrese una opción: ");
                opcion = scanner.nextInt();
                
                switch (opcion) {
                    case 1 -> mostrarPlanilla();
                    case 2 -> agregarTrabajador();
                    case 3 -> buscarTrabajador();
                    case 4 -> mostrarListadoTrabajadores();
                    default -> System.out.println("Opción inválida.");
                }
            }
        }
    }

    public static void mostrarPlanilla() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el DNI del trabajador: ");
        String dni = scanner.nextLine();

        Trabajador trabajadorEncontrado = buscarTrabajadorPorDni(dni);

        if (trabajadorEncontrado != null) {
            System.out.println("PLANILLA");
            System.out.println("Nombre: " + trabajadorEncontrado.getNombre() + " " + trabajadorEncontrado.getApellido());
            System.out.println("DNI: " + trabajadorEncontrado.getDni());
            System.out.println("Sueldo: " + trabajadorEncontrado.getSueldo());
            System.out.println("Descuentos AFP (13%): " + (trabajadorEncontrado.getSueldo() * 0.13));
            System.out.println("Descuentos seguro salud (9%): " + (trabajadorEncontrado.getSueldo() * 0.09));
            System.out.println("Descuentos renta quinta (> 4950 desc. 8%): " + (trabajadorEncontrado.getSueldo() > 4950 ? trabajadorEncontrado.getSueldo() * 0.08 : 0));
            System.out.println("Asignación familiar: 103");
            double totalDescuento = (trabajadorEncontrado.getSueldo() * 0.13)
                    + (trabajadorEncontrado.getSueldo() * 0.09)
                    + (trabajadorEncontrado.getSueldo() > 4950 ? trabajadorEncontrado.getSueldo() * 0.08 : 0);
            System.out.println("Total descuento: " + totalDescuento);
            double montoNeto = trabajadorEncontrado.getSueldo() - totalDescuento;
            System.out.println("Monto neto: " + montoNeto);
            System.out.println("Fecha inicio: " + trabajadorEncontrado.getFechaInicio());
            System.out.println("Fecha retiro: " + trabajadorEncontrado.getFechaRetiro());
        } else {
            System.out.println("Trabajador no encontrado.");
        }
    }

    public static void agregarTrabajador() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("AGREGAR TRABAJADOR");

        System.out.print("Ingrese el DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese el sueldo: ");
        double sueldo = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer del scanner
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

        trabajadores.add(nuevoTrabajador);
        System.out.println("Trabajador agregado correctamente.");
    }

    public static void buscarTrabajador() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("BÚSQUEDA DE TRABAJADOR");
        System.out.println("1. Buscar por DNI");
        System.out.println("2. Buscar por nombre");
        System.out.print("Ingrese una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el DNI del trabajador: ");
                String dni = scanner.nextLine();

                Trabajador trabajadorEncontrado = buscarTrabajadorPorDni(dni);

                if (trabajadorEncontrado != null) {
                    mostrarDetallesTrabajador(trabajadorEncontrado);
                } else {
                    System.out.println("Trabajador no encontrado.");
                }
                break;
            case 2:
                System.out.print("Ingrese el nombre del trabajador: ");
                String nombre = scanner.nextLine();

                List<Trabajador> trabajadoresEncontrados = buscarTrabajadoresPorNombre(nombre);

                if (!trabajadoresEncontrados.isEmpty()) {
                    System.out.println("Trabajadores encontrados:");
                    for (Trabajador trabajador : trabajadoresEncontrados) {
                        mostrarDetallesTrabajador(trabajador);
                    }
                } else {
                    System.out.println("No se encontraron trabajadores con ese nombre.");
                }
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    public static void mostrarListadoTrabajadores() {
        System.out.println("LISTADO DE TRABAJADORES");

        for (Trabajador trabajador : trabajadores) {
            mostrarDetallesTrabajador(trabajador);
        }
    }

    public static void mostrarDetallesTrabajador(Trabajador trabajador) {
        System.out.println("DNI: " + trabajador.getDni());
        System.out.println("Nombre: " + trabajador.getNombre());
        System.out.println("Apellido: " + trabajador.getApellido());
        System.out.println("Sueldo: " + trabajador.getSueldo());
        System.out.println("Descuentos AFP (13%): " + (trabajador.getSueldo() * 0.13));
        System.out.println("Descuentos seguro salud (9%): " + (trabajador.getSueldo() * 0.09));
        System.out.println("Descuentos renta quinta (> 4950 desc. 8%): " + (trabajador.getSueldo() > 4950 ? trabajador.getSueldo() * 0.08 : 0));
        System.out.println("Asignación familiar: 103");
        double totalDescuento = (trabajador.getSueldo() * 0.13) + (trabajador.getSueldo() * 0.09) + (trabajador.getSueldo() > 4950 ? trabajador.getSueldo() * 0.08 : 0);
        System.out.println("Total descuento: " + totalDescuento);
        double montoNeto = trabajador.getSueldo() - totalDescuento;
        System.out.println("Monto neto: " + montoNeto);
        System.out.println("Fecha inicio: " + trabajador.getFechaInicio());
        System.out.println("Fecha retiro: " + trabajador.getFechaRetiro());

        System.out.println("----------------------------------");
    }

    public static Trabajador buscarTrabajadorPorDni(String dni) {
        for (Trabajador trabajador : trabajadores) {
            if (trabajador.getDni().equals(dni)) {
                return trabajador;
            }
        }
        return null;
    }

    public static List<Trabajador> buscarTrabajadoresPorNombre(String nombre) {
        List<Trabajador> trabajadoresEncontrados = new ArrayList<>();

        for (Trabajador trabajador : trabajadores) {
            if (trabajador.getNombre().equalsIgnoreCase(nombre)) {
                trabajadoresEncontrados.add(trabajador);
            }
        }

        return trabajadoresEncontrados;
    }
}
