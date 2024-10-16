/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Rutina {
    private String nombre;
    private ArrayList<Ejercicio> ejercicios;
    
    public Rutina(String nombre, ArrayList<Ejercicio> ejercicios){
        this.nombre = nombre;
        this.ejercicios = ejercicios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(ArrayList<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rutina other = (Rutina) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.ejercicios, other.ejercicios)) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "Rutina{" + "nombre=" + nombre + ", ejercicios=" + ejercicios + " }";
    }


    
    public static ArrayList<Rutina> cargarListaRutinas(String nombreArchivo) throws GymException{
     ArrayList<Rutina> rutinas = new ArrayList<>();
        try(
                FileReader reader = new FileReader(nombreArchivo);
                BufferedReader br = new BufferedReader(reader); 
            ){
            String linea;
            while((linea = br.readLine())!=null){
                ArrayList<Ejercicio> ejercicios = new ArrayList<>();
                String[] tokens = linea.split("\\;");
                String nombreRutina = tokens[0];
                for (int i = 1; i < tokens.length; i++) {
                    ArrayList<String> imagenes = new ArrayList<>();
                    String[] str = tokens[i].split(",");
                    String nomEjercicio = str[0];
                    int repeticiones = Integer.parseInt(str[1]);
                    imagenes.add(str[2]);
                    imagenes.add(str[3]);
                    Ejercicio ejercicio = new Ejercicio(nomEjercicio,repeticiones,imagenes);
                    ejercicios.add(ejercicio);
                }
                Rutina rutina = new Rutina(nombreRutina , ejercicios);
                rutinas.add(rutina);
            }
            br.close();
            reader.close();
        }catch(IOException e){
            throw new GymException();
        }
        return rutinas;
    }
    public static ArrayList<String> nombresRutina(String nomfile) throws GymException
    {
        ArrayList<String> nombres = new ArrayList<>();
        try{
            ArrayList<Rutina> rutinas = Rutina.cargarListaRutinas(nomfile);
            for (int i =0; i < rutinas.size() ;i++)
            {
                nombres.add(rutinas.get(i).getNombre());
            }
        }
        catch(GymException e)
        {
            throw new GymException();
        }
        return nombres;
    }
    public static ArrayList<Ejercicio> cargarListaEjercicio(String nomfile, String str) throws GymException
    {    
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        try {
            ArrayList<String> nomRutinas = Rutina.nombresRutina(nomfile);
            int pos = nomRutinas.indexOf(str);
            ArrayList<Rutina> rutinas = Rutina.cargarListaRutinas(nomfile);  
            ejercicios = rutinas.get(pos).getEjercicios();
        } catch (GymException ex) {
            ex.getMessage();
        }
        return ejercicios;
    }
    public static ArrayList<String> nombresEjercicio(String nomfile, String str) throws GymException
    {
        ArrayList<String> nombres = new ArrayList<>();
        try{
            ArrayList<Ejercicio> ejercicios = Rutina.cargarListaEjercicio(nomfile, str);
                for (int j =0; j < ejercicios.size() ;j++)
                {
                    nombres.add(ejercicios.get(j).getNombre());
                }
        }
        catch(GymException e)
        {
            throw new GymException();
        }
        return nombres;
    }
    public static ArrayList<Integer> cargarRepeticiones(String nomfile, String str) throws GymException
    {    
        ArrayList<Integer> repeticiones = new ArrayList<>();
        try {  
            ArrayList<Ejercicio> ejercicios = Rutina.cargarListaEjercicio(nomfile, str);
            for (int j =0; j < ejercicios.size() ;j++)
                {
                    repeticiones.add(ejercicios.get(j).getRepeticiones());
                }
            
        } catch (GymException ex) {
            ex.getMessage();
        }
        return repeticiones;
    }
}
