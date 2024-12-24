/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fernandohernandez.proyecto2.modelos;

import java.util.ArrayList;

/**
 *
 * @author ferhm
 */
public class PlatoFuerte extends Producto {
    
    //Atributos de la clase PlatoFuerte
    private ArrayList<String> ingredientes;
    
    //Constructores, la palabra reservada super hace referencia al constructor de la super clase
    public PlatoFuerte(String codigo, String nombre, boolean LibreGluten, int cantidadPorciones, double precio, ArrayList<String> ingredientes) {
        super(codigo, nombre, LibreGluten, cantidadPorciones, precio);
        ingredientes = ingredientes;
        
    }

    public PlatoFuerte() {
        super();
        ingredientes = new ArrayList<>();
    }

    //Sets de los atributos
    public boolean setIngredientes(ArrayList<String> ingredientes) {
        //Pequeña validación para que siempre sean al menos tres ingredientes
        if (ingredientes.size() >= 3) {
            this.ingredientes = ingredientes;
            return true;
        }
        return false;
    }
    
    //Gets de los atributos

    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }
    
    //toString
    @Override
    public String toString() {
        return super.toString() + "PlatoFuerte{" + "ingredientes=" + ingredientes + '}';
    }
    
    //Otras funciones
    boolean validarIngredientes () {
        //Aquí se valida que los ingredientes sean de verdad el mínimo de tres, sino devuelve false
        return (ingredientes.size() >=3);
    }
}