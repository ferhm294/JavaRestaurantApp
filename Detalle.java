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
public class Detalle implements Serializable {
    
    //Atributos de la clase Detalle para usarse en las líneas de detalle de la orden
    private Producto producto;
    private int cantidad;
    
    //Constructores
    
    public Detalle () {
        producto = null;
        cantidad = 0;
    }
    
    public Detalle (Producto pro, int cant) {
        producto = pro;
        cantidad = cant;
    }
    
    //Sets de los atributos
    public void setProducto (Producto pro) {
        producto = pro;
    }
    
    public void setCantidad (int cant) {
        cantidad = cant;
    }
    
    //Gets de los atributos
    public Producto getProducto() {
        return producto;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    //toString
    @Override
    public String toString() {
        return "Detalle: {Producto: " + producto +
                ", Cantidad: " + cantidad + "}";
    }
}