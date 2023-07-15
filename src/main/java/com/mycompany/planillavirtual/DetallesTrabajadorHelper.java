
package com.mycompany.planillavirtual;

public class DetallesTrabajadorHelper {
    
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
    
    
}
