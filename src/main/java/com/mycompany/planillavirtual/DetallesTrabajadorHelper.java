package com.mycompany.planillavirtual;

public class DetallesTrabajadorHelper {

    public static void imprimirEncabezado() {
        String[] encabezado = {"#", "DNI", "NOMBRE", "APELLIDO", "SUELDO", "FECHA INICIO"};
        imprimirLinea(encabezado.length);
        imprimirFila(encabezado);
        imprimirLinea(encabezado.length);
    }

    public static void imprimirLinea(int numColumnas) {
        System.out.print("+");
        for (int i = 0; i < numColumnas; i++) {
            for (int j = 0; j < 12; j++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println();
    }

    public static void imprimirFila(String[] fila) {
        System.out.print("|");
        for (String columna : fila) {
            System.out.printf("%-12s|", columna);
        }
        System.out.println();
    }

    public static void imprimirFilaTrabajador(int numero, Trabajador trabajador) {
        String[] fila = {
            Integer.toString(numero),
            trabajador.getDni(),
            trabajador.getNombre(),
            trabajador.getApellido(),
            "S/." + Double.toString(trabajador.getSueldo()),
            trabajador.getFechaInicio()
        };
        imprimirFila(fila);
        imprimirLinea(fila.length);
    }

    public static void mostrarDetallesTrabajador(int numero, Trabajador trabajador) {
        String[] fila = {
            Integer.toString(numero),
            trabajador.getDni(),
            trabajador.getNombre(),
            trabajador.getApellido(),
            "S/." + Double.toString(trabajador.getSueldo()),
            trabajador.getFechaInicio()
        };
        imprimirFila(fila);
        imprimirLinea(fila.length);
    }

    public static void mostrarDetallesTrabajador(Trabajador trabajador) {
        System.out.println("+---------------------------------------------------------------------+");
        System.out.println("   DETALLES DEL TRABAJADOR  ");
        System.out.println("DNI: " + trabajador.getDni());
        System.out.println("Nombre: " + trabajador.getNombre());
        System.out.println("Apellido: " + trabajador.getApellido());
        System.out.println("Sueldo: " + "s/. " +trabajador.getSueldo());
        System.out.println("Descuentos AFP (13%): " + "s/. " + (trabajador.getSueldo() * 0.13));
        System.out.println("Descuentos seguro salud (9%): " + "s/. " + (trabajador.getSueldo() * 0.09));
        System.out.println("Descuentos renta quinta (> 4950 desc. 8%): " + "s/. " + (trabajador.getSueldo() > 4950 ? trabajador.getSueldo() * 0.08 : 0));

        int numCargaFamiliar = trabajador.getNumCargaFamiliar();
        double asignacionFamiliar = 103 * numCargaFamiliar;
        System.out.println("Asignación familiar: " + "s/. " + asignacionFamiliar);

        double totalDescuento = (trabajador.getSueldo() * 0.13) + (trabajador.getSueldo() * 0.09) + (trabajador.getSueldo() > 4950 ? trabajador.getSueldo() * 0.08 : 0);
        System.out.println("Total descuento: " + "s/. " + totalDescuento);
        double montoNeto = trabajador.getSueldo() - totalDescuento;
        System.out.println("Monto neto: " + "s/. " + montoNeto);
        System.out.println("Fecha inicio: " + trabajador.getFechaInicio());
        System.out.println("Fecha retiro: " + trabajador.getFechaRetiro());
        System.out.println("+---------------------------------------------------------------------+");
    }

}
