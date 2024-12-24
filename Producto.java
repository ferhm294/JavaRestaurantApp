/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fernandohernandez.proyecto2.modelos;

import java.io.Serializable;

/**
 *
 * @author ferhm
 */
public abstract class Producto implements Serializable {
    private String codigo;
    private String nombre;
    private boolean LibreGluten;
    private int cantidadPorciones;
    private double precio;
    
    
    //Constructores
    public Producto(String codigo, String nombre, boolean LibreGluten, int cantidadPorciones, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.LibreGluten = LibreGluten;
        this.cantidadPorciones = cantidadPorciones;
        this.precio = precio;
    }
    
    public Producto() {
        this.codigo = "";
        this.nombre = "";
        this.LibreGluten = false;
        this.cantidadPorciones = 0;
        this.precio = 0.0;
    }

    //Sets de los atributos
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLibreGluten(boolean LibreGluten) {
        this.LibreGluten = LibreGluten;
    }

    public void setCantidadPorciones(int cantidadPorciones) {
        this.cantidadPorciones = cantidadPorciones;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    //Gets de los atributos
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isLibreGluten() {
        return LibreGluten;
    }

    public int getCantidadPorciones() {
        return cantidadPorciones;
    }

    public double getPrecio() {
        return precio;
    }
    
    //Otras funciones
    public String reporteProducto() {
        return nombre + "(Precio Unitario: " + precio + ")";
    }
    
    //toString
    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", nombre=" + nombre + ", LibreGluten=" + LibreGluten + ", cantidadPorciones=" + cantidadPorciones + ", precio=" + precio + '}';
    }
}
