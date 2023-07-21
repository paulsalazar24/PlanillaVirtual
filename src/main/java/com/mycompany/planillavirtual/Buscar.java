/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.planillavirtual;

import static com.mycompany.planillavirtual.DetallesTrabajadorHelper.mostrarDetallesTrabajador;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Melqui
 */
public class Buscar {
   
    public static void buscarTrabajador(List<Trabajador> trabajadores) {
        
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

                Trabajador trabajadorEncontrado = buscarTrabajadorPorDni(dni,trabajadores);

                if (trabajadorEncontrado != null) {
                    mostrarDetallesTrabajador(trabajadorEncontrado);
                } else {
                    System.out.println("Trabajador no encontrado.");
                }
            }
            case 2 -> {
                System.out.print("Ingrese el nombre del trabajador: ");
                String nombre = scanner.nextLine();

                List<Trabajador> trabajadoresEncontrados = buscarTrabajadoresPorNombre(nombre,trabajadores);

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

    public static Trabajador buscarTrabajadorPorDni(String dni,List<Trabajador> trabajadores) {
        for (Trabajador trabajador : trabajadores) {
            if (trabajador.getDni().equals(dni)) {
                return trabajador;
            }
        }
        return null;
    }

    public static List<Trabajador> buscarTrabajadoresPorNombre(String nombre,List<Trabajador> trabajadores) {
        List<Trabajador> trabajadoresEncontrados = new ArrayList<>();

        for (Trabajador trabajador : trabajadores) {
            if (trabajador.getNombre().equalsIgnoreCase(nombre)) {
                trabajadoresEncontrados.add(trabajador);
            }
        }

        return trabajadoresEncontrados;
    }
    
            
            
    
    
}
