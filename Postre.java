/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fernandohernandez.proyecto2.modelos;

/**
 *
 * @author ferhm
 */
public class Postre extends Producto {

    //Atributos de la clase Postre
    private String tipoSabor;
    
    //Constructores, la palabra reservada super hace referencia al constructor de la super clase
    public Postre(String codigo, String nombre, boolean LibreGluten, int cantidadPorciones, double precio, String tipoSabor) {
        super(codigo, nombre, LibreGluten, cantidadPorciones, precio);
        this.tipoSabor = tipoSabor;
    }

    public Postre() {
        super();
        tipoSabor = "";
    }

    //Sets de los atributos
    public void setTipoSabor(String tipoSabor) {
        this.tipoSabor = tipoSabor;
    }
    
    //Gets de los atributos

    public String getTipoSabor() {
        return tipoSabor;
    }
    
    //toString
    @Override
    public String toString() {
        return super.toString() + "Postre{" + "tipoSabor=" + tipoSabor + '}';
    }
}