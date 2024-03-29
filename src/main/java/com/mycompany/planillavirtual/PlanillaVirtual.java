package com.mycompany.planillavirtual;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class PlanillaVirtual {

    private static List<Trabajador> trabajadores;
    private static final String RUTA_ARCHIVO_JSON = "src/main/java/datos/trabajadores.json";
    private static Usuario usuarioActual;
    private static List<Usuario> usuarios;

    public static void main(String[] args) {
        trabajadores = Trabajador.cargarTrabajadoresDesdeJSON(RUTA_ARCHIVO_JSON);
        usuarios = new ArrayList<>();
        iniciarSesion();
    }

    public static void iniciarSesion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("      BIENVENIDO AL SISTEMA DE PLANILLA VIRTUAL  ");
        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        // Verificar las credenciales del usuario
        if (nombreUsuario.equals("admin") && password.equals("admin123")) {
            usuarioActual = new Usuario(nombreUsuario, password, true);
            System.out.println("Bienvenido, " + nombreUsuario + " (Administrador)");
            mostrarMenu();
        } else {
            Usuario usuarioEncontrado = buscarUsuario(nombreUsuario, password);
            if (usuarioEncontrado != null) {
                usuarioActual = usuarioEncontrado;
                System.out.println("Bienvenido, " + nombreUsuario);
                mostrarMenu();
            } else {
                System.out.println("Credenciales inválidas. Intente nuevamente.");
                iniciarSesion();
            }
        }
    }

    public static void mostrarMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcion = 0;

            while (opcion != 6) {
                System.out.println("MENU");
                System.out.println("1. Planilla");
                System.out.println("2. Agregar trabajador");
                System.out.println("3. Búsqueda de trabajador");
                System.out.println("4. Trabajadores");

                if (usuarioActual != null && usuarioActual.esAdmin()) {
                    System.out.println("5. Crear usuario");
                }

                System.out.println("6. Cerrar sesión");
                System.out.print("Ingrese una opción: ");

                opcion = scanner.nextInt();
                scanner.nextLine();
                if (usuarioActual.esAdmin()) {
                    switch (opcion) {
                        case 1 ->
                            mostrarPlanilla();
                        case 2 ->
                            agregarTrabajador();
                        case 3 ->
                            Buscar.buscarTrabajador(trabajadores);
                        case 4 ->
                            mostrarListadoTrabajadores();
                        case 5 ->
                            crearUsuario();
                        case 6 -> {
                            usuarioActual = null;
                            System.out.println("Sesión cerrada.");
                            return;
                        }
                        default ->
                            System.out.println("Opción inválida.");
                    }
                } else {
                    switch (opcion) {
                        case 1 ->
                            mostrarPlanilla();
                        case 3 ->
                            Buscar.buscarTrabajador(trabajadores);
                        case 4 ->
                            mostrarListadoTrabajadores();
                        case 6 -> {
                            usuarioActual = null;
                            System.out.println("Sesión cerrada.");
                            return;
                        }
                        default ->
                            System.out.println("Opción inválida.");
                    }
                }
            }
        }
    }

    public static void mostrarPlanilla() {
        MostrarPlanilla.mostrarPlanilla(trabajadores);
    }

    public static void agregarTrabajador() {
        AgregarTrabajador.agregarTrabajador(trabajadores);
    }

    
    public static void mostrarListadoTrabajadores() {
        DetallesTrabajadorHelper.imprimirEncabezado();
        int contador = 1;
        for (Trabajador trabajador : trabajadores) {
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
        DetallesTrabajadorHelper.imprimirLinea(6);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el DNI del trabajador para ver la planilla: ");
        String dniTrabajador = scanner.nextLine();

        Trabajador trabajadorSeleccionado = Buscar.buscarTrabajadorPorDni(dniTrabajador,trabajadores);

        if (trabajadorSeleccionado == null) {
            System.out.println("No se encontró un trabajador con ese DNI.");
            return;
        }

        DetallesTrabajadorHelper.mostrarDetallesTrabajador(trabajadorSeleccionado);

        System.out.println();
    }

    public static Usuario buscarUsuario(String nombreUsuario, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombreUsuario) && usuario.getPassword().equals(password)) {
                return usuario;
            }
        }
        return null;
    }

    public static void crearUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("CREAR USUARIO");

        if (!usuarioActual.esAdmin()) {
            System.out.println("Acceso denegado. Solo los administradores pueden crear usuarios.");
            return;
        }

        while (true) {
            System.out.print("Ingrese el nombre de usuario: ");
            String nombreUsuario = scanner.nextLine();
            System.out.print("Ingrese la contraseña: ");
            String password = scanner.nextLine();
            System.out.print("¿Es administrador? (Sí/No): ");
            String esAdminInput = scanner.nextLine();
            boolean esAdmin = esAdminInput.equalsIgnoreCase("Sí");

            Usuario nuevoUsuario = new Usuario(nombreUsuario, password, esAdmin);
            usuarios.add(nuevoUsuario);

            System.out.println("¡Felicitaciones! El usuario ha sido creado exitosamente.");

            System.out.print("¿Desea continuar creando usuarios? (Sí/No): ");
            String continuarInput = scanner.nextLine();

            if (continuarInput.equalsIgnoreCase("No")) {
                break;
            }
        }

        System.out.print("¿Desea iniciar sesión con el último usuario creado? (Sí/No): ");
        String iniciarSesionInput = scanner.nextLine();

        if (iniciarSesionInput.equalsIgnoreCase("Sí")) {
            usuarioActual = usuarios.get(usuarios.size() - 1);
            mostrarMenu();
        } else {
            mostrarMenu();
        }
    }
}
