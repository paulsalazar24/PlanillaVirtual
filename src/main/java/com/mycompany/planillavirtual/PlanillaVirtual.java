package com.mycompany.planillavirtual;


import java.util.List;
import java.util.Scanner;

public class PlanillaVirtual {
    private static List<Trabajador> trabajadores;
    private static final String RUTA_ARCHIVO_JSON = "./datos/trabajadores.json";


    public static void main(String[] args) {
        trabajadores = Trabajador.cargarTrabajadoresDesdeJSON(RUTA_ARCHIVO_JSON);

        mostrarMenu();
    }

    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
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
                case 1:
                    mostrarPlanilla();
                    break;
                case 2:
                    agregarTrabajador();
                    break;
                case 3:
                    buscarTrabajador();
                    break;
                case 4:
                    mostrarListadoTrabajadores();
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }

        scanner.close();
    }

    public static void mostrarPlanilla() {
        // Implementa aquí la lógica para mostrar la planilla de un trabajador
        // Puedes utilizar un método en la clase Trabajador para calcular los descuentos y el monto neto
        // Recuerda que debes mostrar la planilla para cada trabajador en la lista de trabajadores
    }

    public static void agregarTrabajador() {
        // Implementa aquí la lógica para agregar un nuevo trabajador
        // Puedes pedir los datos al usuario y crear un nuevo objeto Trabajador
        // Luego, agrega el nuevo trabajador a la lista de trabajadores
    }

    public static void buscarTrabajador() {
        // Implementa aquí la lógica para buscar un trabajador por su DNI o nombre
        // Puedes pedir los datos al usuario y recorrer la lista de trabajadores para encontrar la coincidencia
        // Luego, muestra los detalles del trabajador encontrado
    }

    public static void mostrarListadoTrabajadores() {
    for (Trabajador trabajador : trabajadores) {
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
}

    
    
}

