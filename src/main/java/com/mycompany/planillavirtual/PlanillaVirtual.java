package com.mycompany.planillavirtual;

import static com.mycompany.planillavirtual.DetallesTrabajadorHelper.mostrarDetallesTrabajador;
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
                            buscarTrabajador();
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
                            buscarTrabajador();
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
        MostrarPlanilla.mostrarPlanilla();
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
        scanner.nextLine();
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
        scanner.nextLine();

        switch (opcion) {
            case 1 -> {
                System.out.print("Ingrese el DNI del trabajador: ");
                String dni = scanner.nextLine();

                Trabajador trabajadorEncontrado = buscarTrabajadorPorDni(dni);

                if (trabajadorEncontrado != null) {
                    mostrarDetallesTrabajador(trabajadorEncontrado);
                } else {
                    System.out.println("Trabajador no encontrado.");
                }
            }
            case 2 -> {
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
            }
            default ->
                System.out.println("Opción inválida.");
        }
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

        Trabajador trabajadorSeleccionado = buscarTrabajadorPorDni(dniTrabajador);

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
