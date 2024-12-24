/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fernandohernandez.proyecto2.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author ferhm
 */
public class Orden implements Serializable {
    
    //Atributos de la clase Orden
    private String numOrden, nombreCliente,codigoDescuento;
    private ArrayList<Detalle> detalles = new ArrayList<>();
    private double total, subTotal, montoTotalProductos, calculoServicio, calculoIVA, descuento;
    LocalDate fecha;
    
    //Constructores
    public Orden (String numOrd, String nomCli) {
        numOrden = numOrd;
        fecha = LocalDate.now();
        nombreCliente = nomCli;
        codigoDescuento = "0";
        total = 0.0;
    }
    
    public Orden () {
        numOrden = "";
        fecha = LocalDate.now();
        nombreCliente = "";
        codigoDescuento = "0";
        total = 0.0;
    }
    
    //Sets de los atributos
    public void setNumOrden(String numOrd) {
        numOrden = numOrd;
    }
    
    public void setFecha(String fech) {
        fecha = LocalDate.now();
    }
    
    public void setDetalles(ArrayList<Detalle> detal) {
        detalles = detal;
    }
    
    public void setNombreCliente(String nomCli) {
        nombreCliente = nomCli;
    }
    
    public void setCodigoDescuento(String desc) {
        codigoDescuento = desc;
    }
    
    public void setTotal(double tot) {
        total = tot;
    }
    
    public void setSubTotal(double subTot) {
        total = subTot;
    }
    
    public void setMontoTotalProductos(double mont) {
        montoTotalProductos = mont;
    }
    
    public void setCalculoServicio(double mont) {
        calculoServicio = mont;
    }
    
    public void setCalculoIVA(double mont) {
        calculoIVA = mont;
    }
    
    public void SetDescuento(double mont) {
        descuento = mont;
    }
    
    //Gets de los atributos
    public String getNumOrden () {
        return numOrden;
    }
    
    public LocalDate getFecha () {
        return fecha;
    }
    
    public ArrayList<Detalle> getDetalles() {
        return detalles;
    }
    
    public String getNombreCliente () {
        return nombreCliente;
    }
    
    public String getCodigoDescuento () {
        return codigoDescuento;
    }
    
    public double getTotal () {
        return total;
    }
    
    public double getSubTotal () {
        return subTotal;
    }
    
    public double getMontoTotalProductos () {
        return montoTotalProductos;
    }
    
    public double getCalculoServicio () {
        return calculoServicio;
    }
    
    public double getCalculoIVA () {
        return calculoIVA;
    }
    
    public double getDescuento () {
        return descuento;
    }
    
    //toString
    @Override
    public String toString () {
        StringBuilder cadena = new StringBuilder();
        cadena.append("Orden{")
                .append("Número de orden: ").append(numOrden)
                .append("Fecha de la orden: ").append(fecha)
                .append("Cliente: ").append(nombreCliente)
                .append("Detalles: [");
        for (Detalle det : detalles) {
            cadena.append(det).append(", "); //Se omite el toString() porque el compilador lo llama automáticamente
        }
        
        if (!detalles.isEmpty()) {
            cadena.setLength(cadena.length() - 2);
        }
        cadena.append("]}");
        return cadena.toString();
    }
    
    //Otras funciones
    public void agregarDetalle (Detalle det) {
        detalles.add(det);
    }
    
    public boolean validarNumOrd (String numOrd) {
       return numOrd.matches("ORD\\d{4}");
    }
    
    public boolean validarFecha (String fech) {
       SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
       formatoFecha.setLenient(false); //Esto se utiliza para que la conversión tenga que coincidir estrictamente con el formato especificado en el objeto SimpleDateFormat
       try {
           Date fechaPrueba; //Aquí el objeto fecha no se utiliza para nada más que para hacer la conversión y ver si lanza ParseException
           fechaPrueba = formatoFecha.parse(fech);
           return true;
       } catch (ParseException e) {
           //Si lanza error la conversión significa que el formato no es el adecuado y se procede a devolver false
           return false;
       }
    }
    
    public boolean validarCodigoDescuento (String desc) {
        //En este método validamos que solamente se acepten los códigos DESC5, DESC10, DESC15 y 0
        return (desc.matches("DESC5")) || (desc.matches("DESC10")) || (desc.matches("DESC15")) || (desc.matches("0"));
    }
    
    public void calcularTotal () {
        /*Para este punto ya se validí si la orden es aplicable a descuento o no y por ende 
        no hace falta calcular montoTotalProductos porque eso ya se hizo en ese otro método
        */
        //Se procede a calcular cada uno de los porcentajes que dependen del monto total
        calculoServicio = montoTotalProductos * 0.10;
        calculoIVA = montoTotalProductos * 0.13;
        //Se calcula un subtotal para luego restarle el descuento de ser necesario
        subTotal = montoTotalProductos + calculoServicio + calculoIVA;
        //Se valida si el descuento se debe de aplicar o no
        switch (codigoDescuento) {
            case "DESC5":
                descuento = subTotal * 0.05;
                break;
            case "DESC10":
                descuento = subTotal * 0.10;
                break;
            case "DESC15":
                descuento = subTotal * 0.15;
                break;
            case "0":
                descuento = 0.0;
                break;
        }
        //Finalmente calcula el total final de la orden
        total = subTotal - descuento;
    }
    
    public boolean aplicaDescuento() {
        /*Se recorren todos los detalles para sumar el monto total primero
        y de esta forma poder validar si la orden es aplicable a descuento o no
        */
        for (Detalle det : detalles) {
            montoTotalProductos += det.getCantidad() * det.getProducto().getPrecio();
        }
        return (montoTotalProductos > 10000.0);
    }
    
    /*public boolean isValid() {
        //Aquí se valida si la orden cuenta con al menos un plato fuerte y un postre
        boolean banderaPlatoFuerte = false, banderaPostre = false;
        for(Detalle det : detalles) {
            if (det.getProducto() instanceof PlatoFuerte) {
                banderaPlatoFuerte = true;
            } else {
                banderaPostre = true;
            }
        }
        return banderaPlatoFuerte && banderaPostre;
    }*/
    
    public String reporteDetalles() {
        String reporteDetalles = "";
        ArrayList<PlatoFuerte> PlatosFuertes = new ArrayList<>();
        ArrayList<Postre> Postres = new ArrayList<>();
        for (Detalle det : detalles) { // Se recorren los detalles para obtener los platos fuertes separados de los postres
            Producto prod = det.getProducto();
            if (prod instanceof PlatoFuerte) {
                PlatoFuerte PF = (PlatoFuerte) prod; //Downcasting necesario par poder agregar el platofuerte al arreglo de platos fuertes
                PlatosFuertes.add(PF); //Esta agregación el compilador no la acepta sin el downcasting
            } else {
                Postre PT = (Postre) prod; //Downcasting necesario par poder agregar el postre al arreglo de platos fuertes
                Postres.add(PT); //Esta agregación el compilador no la acepta sin el downcasting
            }
        }
        if (PlatosFuertes.size() == 0) { //Reporte de los platos fuertes
            reporteDetalles += "Platos fuertes: 0\n"; //En caso de que no se pidieron platosfuertes en esta orden
        } else {
            StringBuilder cadena = new StringBuilder(); //StringBuilder necesario para hacer uso del .setLength
            cadena.append("Platos fuertes: ");
            for (PlatoFuerte PF : PlatosFuertes) {
                cadena.append(PF.reporteProducto())
                        .append(", ");
            }
            cadena.setLength(cadena.length() - 2); //Elimino el último , espacio
            cadena.append("\n");
            reporteDetalles += cadena.toString(); //Se convierte la cadena a String y se agrega a la variable que se va a devolver
        }
        if (Postres.size() == 0) { //Reporte de los postres
            reporteDetalles += "Postres: 0\n"; //En caso de que no se pidieron postres en esta orden
        } else {
            StringBuilder cadena = new StringBuilder(); //StringBuilder necesario para hacer uso del .setLength
            cadena.append("Postres: ");
            for (Postre PT : Postres) {
                cadena.append(PT.reporteProducto())
                        .append(", ");
            }
            cadena.setLength(cadena.length() - 2); //Elimino el último , espacio
            cadena.append("\n");
            reporteDetalles += cadena.toString(); //Se convierte la cadena a String y se agrega a la variable que se va a devolver
        }
        return reporteDetalles;
    }
}