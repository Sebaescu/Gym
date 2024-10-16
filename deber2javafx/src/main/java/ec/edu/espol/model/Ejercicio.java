/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.util.ArrayList;


public class Ejercicio implements Comparable<Ejercicio>{
    private String nombre;
    private int repeticiones;
    private ArrayList<String> imagenes;
    
    public Ejercicio(String nombre, int repeticiones, ArrayList<String> imagenes){
        this.nombre = nombre;
        this.repeticiones = repeticiones;
        this.imagenes = imagenes;
    }

    public String getNombre() {
        return nombre;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public ArrayList<String> getImagenes() {
        return imagenes;
    }

    @Override
    public String toString() {
        return "Nombre = " + nombre + ",Repeticiones =" + repeticiones + ",Imagenes = " + imagenes + " }";
    }

    @Override
    public int compareTo(Ejercicio ej) {
        if(!this.nombre.equals(ej.getNombre()))
            return this.nombre.compareTo(ej.getNombre());
        else if (this.repeticiones > ej.getRepeticiones())
            return 1;
        return -1;
    }

}
