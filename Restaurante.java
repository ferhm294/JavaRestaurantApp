/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fernandohernandez.proyecto2.modelos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author ferhm
 */
public class Restaurante implements Serializable {
    
    //Atributos de la clase Restaurante
    private ArrayList<Producto> menu;
    private ArrayList<Orden> ordenes;
    private int contadorNumOrd;
    
    //Constructores
    public Restaurante(int contadorNumOrd) {
        this.menu = new ArrayList<>();
        this.ordenes = new ArrayList<>();
        this.contadorNumOrd = contadorNumOrd;
    }
    
    public Restaurante() {
        this.menu = new ArrayList<>();
        this.ordenes = new ArrayList<>();
        this.contadorNumOrd = 0;
    }
    
    //Sets de los atributos
    public void setMenu (ArrayList<Producto> menu) {
        this.menu = menu;
    }
    
    public void setOrdenes (ArrayList<Orden> ord) {
        ordenes = ord;
    }
    
    public void setContadorNumOrd(int contadorNumOrd) {
        this.contadorNumOrd = contadorNumOrd;
    }
    
    //Gets de los atributos
    public ArrayList<Producto> getMenu () {
        return menu;
    }
    
    public ArrayList<Orden> getOrdenes () {
        return ordenes;
    }
    
    public int getContadorNumOrd() {
        return contadorNumOrd;
    }
    
    //toString
    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder();
        cadena.append("Restaurante: ")
                .append("Menu: [");
        menu.forEach((prod)-> {
            cadena.append(prod).append(", "); //Se omite el toString() porque el compilador lo llama automáticamente
        });
        if (!menu.isEmpty()) {
            cadena.setLength(cadena.length() - 2);
        }
        cadena.append("], Ordenes: {");
        ordenes.forEach((ord)-> {
            cadena.append(ord).append(", "); //Se omite el toString() porque el compilador lo llama automáticamente
        });
        if (!ordenes.isEmpty()) {
            cadena.setLength(cadena.length() - 2); //Quita la última coma y espacio
        }
        cadena.append("]");
        return cadena.toString();
    }
    
    //Otras funciones
    public void agregarProducto (Producto prod) {
        //Como productos es arraylist se agrega el producto al final del array
        menu.add(prod);
    }
    
    public boolean eliminarProducto (String codigo) {
        return menu.removeIf(men -> men.getCodigo().equals(codigo));
    }
    
    public boolean modificarPlatoFuerte(String codigo, String nombre, boolean libreGluten, int cantidadPorciones, double precio, ArrayList<String> ingredientes) {
        int indice;
        for (Producto prods : menu) {
            if (prods.getCodigo().equals(codigo)) {
                indice = menu.indexOf(prods);

                if (prods instanceof PlatoFuerte) {
                    // Si el producto es un PlatoFuerte, lo modificamos directamente
                    PlatoFuerte plato = (PlatoFuerte) prods;
                    plato.setCodigo(codigo);
                    plato.setNombre(nombre);
                    plato.setLibreGluten(libreGluten);
                    plato.setCantidadPorciones(cantidadPorciones);
                    plato.setPrecio(precio);
                    plato.setIngredientes(ingredientes);

                    menu.set(indice, plato);
                } else {
                    // Si no es un PlatoFuerte, lo eliminamos y creamos un nuevo PlatoFuerte
                    menu.remove(indice);
                    PlatoFuerte nuevoPlato = new PlatoFuerte(codigo, nombre, libreGluten, cantidadPorciones, precio, ingredientes);
                    menu.add(nuevoPlato);
                }
                return true;
            }
        }
        return false;
    }


    
    public boolean modificarPostre(String codigo, String nombre, boolean libreGluten, int cantidadPorciones, double precio, String tipoSabor) {
        int indice;
        for (Producto prods : menu) {
            if (prods.getCodigo().equals(codigo)) {
                indice = menu.indexOf(prods);

                if (prods instanceof Postre) {
                    // Si el producto es un Postre, lo modificamos directamente
                    Postre plato = (Postre) prods;
                    plato.setCodigo(codigo);
                    plato.setNombre(nombre);
                    plato.setLibreGluten(libreGluten);
                    plato.setCantidadPorciones(cantidadPorciones);
                    plato.setPrecio(precio);
                    plato.setTipoSabor(tipoSabor);

                    menu.set(indice, plato);
                } else {
                    // Si no es un Postre, eliminamos el plato fuerte y creamos un nuevo Postre
                    menu.remove(indice);
                    Postre nuevoPostre = new Postre(codigo, nombre, libreGluten, cantidadPorciones, precio, tipoSabor);
                    menu.add(nuevoPostre);
                }
                return true;
            }
        }
        return false;
    }
    
    public String generarNumOrd() {
        if (ordenes.isEmpty()) {
            contadorNumOrd = 0;
            return "ORD0000";
        } else {
            contadorNumOrd = ordenes.size();
            if (contadorNumOrd < 9) {
                return "ORD000" + contadorNumOrd;
            } else if (contadorNumOrd < 99) {
                return "ORD00" + contadorNumOrd;
            } else if (contadorNumOrd < 999) {
                return "ORD0" + contadorNumOrd;
            } else {
                return "ORD" + contadorNumOrd;
            }
        }
    }

    
    public void agregarOrden (Orden ord) {
        //Como ordenes es un arraylist se agrega la orden al final del array
        ordenes.add(ord);
    }
    
    public String reporteMenu () {
        String reporteMenu = "";
        for (Producto prods : menu) {
            reporteMenu = reporteMenu + prods + " ";
        }
        return reporteMenu;
    }
    
    public boolean validarProducto (String codigoProducto) {
        return menu.stream().anyMatch((prods) -> (prods.getCodigo().equals(codigoProducto)));
        /*El .Stream() me convierte al arreglo de productos en un flujo de datos que permite el filtrado o mapeo
        que en este caso nos es bien útil para usar la función .anyMatch() y así realizar la búsqueda sin tantos ciclos
        Seguidamente se hace el recorrido por todo el arreglo con la acción del .equals() para compararlo con el código de producto que se reciba
        */
    }
    
    public String reporteOrdenes () {
        //Creación del reporte final completo con todas las ordenes
        String reporte = "Inicio del reporte\n";
        for (Orden ord : this.ordenes) {
            reporte += "--------------------------------------------------------------------------------------------\n";
            reporte += "Número de orden: " + ord.getNumOrden() + "\n";
            //reporte += "Fecha de la orden: " + ord.getFecha()+ "\n";
            reporte += "Cliente: " + ord.getNombreCliente()+ "\n";
            //reporte += "Producto\tCantidad\tPrecio\n";
            reporte += ord.reporteDetalles();
            //reporte += "Código de descuento: " + ord.getCodigoDescuento()+ "\n";
            //reporte += "Monto total de los productos: " + ord.getMontoTotalProductos()+ "\n";
            //reporte += "Tasa de servicio: " + ord.getCalculoServicio()+ "\n";
            //reporte += "IVA: " + ord.getCalculoIVA()+ "\n";
            //reporte += "Subtotal de la compra: " + ord.getSubTotal() + "\n";
            //reporte += "Descuento aplicado: " + ord.getDescuento()+ "\n";
            reporte += "Total (incluyendo costo de servicio, IVA y descuento): " + ord.getTotal()+ "\n";
            reporte += "--------------------------------------------------------------------------------------------\n";
        }
        reporte += "Fin del reporte\n";
        return reporte;
    }
    
    public String reporteOrdenes (ArrayList<Orden> ordenes) {
        /*Creación del reporte final, recibe un arreglo reducido
        La idea es utilizarlo para reportes con solamente un rango de fechas
        pero queda el código reutilizable si surgen nuevos requerimientos de reportes
        donde se requiera crear un subarreglo de todas las ordenes del restaurante
        */
        String reporte = "Reporte Por Rango de Fechas\n";
        for (Orden ord : ordenes) {
            reporte += "--------------------------------------------------------------------------------------------\n";
            reporte += "Número de orden: " + ord.getNumOrden() + "\n";
            //reporte += "Fecha de la orden: " + ord.getFecha()+ "\n";
            reporte += "Cliente: " + ord.getNombreCliente()+ "\n";
            //reporte += "Producto\tCantidad\tPrecio\n";
            reporte += ord.reporteDetalles();
            //reporte += "Código de descuento: " + ord.getCodigoDescuento()+ "\n";
            //reporte += "Monto total de los productos: " + ord.getMontoTotalProductos()+ "\n";
            //reporte += "Tasa de servicio: " + ord.getCalculoServicio()+ "\n";
            //reporte += "IVA: " + ord.getCalculoIVA()+ "\n";
            //reporte += "Subtotal de la compra: " + ord.getSubTotal() + "\n";
            //reporte += "Descuento aplicado: " + ord.getDescuento()+ "\n";
            reporte += "Total (incluyendo costo de servicio, IVA y descuento): " + ord.getTotal()+ "\n";
            reporte += "--------------------------------------------------------------------------------------------\n";
        }
        reporte += "Fin del reporte\n";
        return reporte;
    }
    
    public String reporteOrdenes (Orden ord) {
        /*Este reporte solamente recibe una oden y se elimina el ciclo
        con el objetivo de imprimir solamente una orden,
        va a ser especialmente útil cuando se requiera sacar un reporte de una orden
        después de realizar una búsqueda con el número de orden.
        Se recibe el objeto Orden por parámetros para dejar la validación en un método aparte y que así este
        reporte se pueda reutilizar en caso de que surgan nuevos requerimientos
        */
        String reporte = "Reporte por Numero de Orden\n";
        reporte += "--------------------------------------------------------------------------------------------\n";
        reporte += "Número de orden: " + ord.getNumOrden() + "\n";
        //reporte += "Fecha de la orden: " + ord.getFecha()+ "\n";
        reporte += "Cliente: " + ord.getNombreCliente()+ "\n";
        //reporte += "Producto\tCantidad\tPrecio\n";
        reporte += ord.reporteDetalles();
        //reporte += "Código de descuento: " + ord.getCodigoDescuento()+ "\n";
        //reporte += "Monto total de los productos: " + ord.getMontoTotalProductos()+ "\n";
        //reporte += "Tasa de servicio: " + ord.getCalculoServicio()+ "\n";
        //reporte += "IVA: " + ord.getCalculoIVA()+ "\n";
        //reporte += "Subtotal de la compra: " + ord.getSubTotal() + "\n";
        //reporte += "Descuento aplicado: " + ord.getDescuento()+ "\n";
        reporte += "Total (incluyendo costo de servicio, IVA y descuento): " + ord.getTotal()+ "\n";
        reporte += "--------------------------------------------------------------------------------------------\n";
        reporte += "Fin del reporte\n";
        return reporte;
    }
    
    public String reportePorRangoFechas (LocalDate inicio, LocalDate fin) { //Se recibe el rango de fechas para el que se requiere el reporte
        ArrayList<Orden> ordenesReporte = busquedaFecha(inicio, fin); //Se buscan las ordenes en el rango de fechas y se guardan en un arreglo temporal
       if (ordenesReporte.size() == 0) { //Evalúa si existen ordenes en este rango de fechas
           return "¡No hay ordenes en este rango de fechas!";
       } else {
           return reporteOrdenes(ordenesReporte); //Si hay, devuelve el reporte solicitado
       }
    }
    
    public String reporteNumOrden (String numOrdenEspecifica) { //Se recibe el número de orden a buscar
        Orden ordenReporte = busquedaNumeroOrden(numOrdenEspecifica); //Se almacena la posible orden a reportar
        if (ordenReporte == null) { //Si el objeto no existe se informa del error
            return "¡Orden no encontrada!";
        } else {
            return reporteOrdenes(ordenReporte); //De lo contrario se devuelve el reporte del objeto correspondiente
        }
    }
    
    public ArrayList<Orden> busquedaFecha (LocalDate inicio, LocalDate fin) {
        //Se recorren todas las ordenes y se retorna un subarreglo de ordenes que estén dentro del rango de fechas recibidas
        ArrayList<Orden> subordenes = new ArrayList<>();
        this.ordenes.forEach((ord) -> { //Con esto se recorre el arreglo
            LocalDate fecha = ord.getFecha(); //Se copia la fecha de la orden que se esté comparando para facilitar la validación
            if (((fecha.isEqual(inicio)) || (fecha.isAfter(inicio))) && ((fecha.isEqual(fin)) || (fecha.isBefore(fin)))) {
                subordenes.add(ord); //Si la fecha está dentro del rango se agrega al subarreglo
            }
        });
        return subordenes;
        /*Se debe de agregar un mensaje de si subordenes es null 
        significa que no se encontraron ordenes en ese rango de fechas
        */
    }
    
    public Orden busquedaNumeroOrden (String numOrd) {
        //Recorre todo el arreglo de ordenes comparando el número de orden recibido por parámetros
        for (Orden ord : ordenes) {
            if (numOrd.equals(ord.getNumOrden())) {
                return ord; //Si lo encuentra devuelve el objeto orden completo
            }
        }
        return null;
        /*Si termina de recorrer el arreglo y no lo encuentra se devuelve null
        por lo que al llamar a este método se debe de evaluar si el return es null
        poner un mensaje de orden no encontrada
        */
    }
    
    public Producto buscarProducto(String codigo) {
        //Se recorre todo el menu para buscar el producto con el código recibido
        for (Producto prod : menu) {
            if (codigo.equals(prod.getCodigo())) {
                //Se devuelve el producto
                return prod;
            }
        }
        //Si al final del arreglo no se encuentra se devuelve null
        return null;
    }
    
    public boolean isMenuValid() {
        boolean banderaPlatoFuerte = false, banderaPostre = false;
        for(Producto prod : menu) {
            if (prod instanceof PlatoFuerte) {
                banderaPlatoFuerte = true;
            } else {
                banderaPostre = true;
            }
        }
        return banderaPlatoFuerte && banderaPostre;
    }
    
    public boolean guardarMenu () throws IOException {
        //Se procede a crear primero el archivo con el nombre solicitado y luego a guardar los objetos como tal porque implementan serializable
        try (FileOutputStream archivoSalida = new FileOutputStream("Menu.txt");
                ObjectOutputStream salida = new ObjectOutputStream(archivoSalida)) {
            salida.writeObject(menu);
            System.out.println("Menú guardado con éxito");
            return true;
        } catch (SecurityException | FileNotFoundException e) { //Multi-catch de SecurityException en caso de no tener permisos para escribir y FileNotFoundException en caso de que el archivo en el que se escribe no exista
            System.out.println("Menú no guardado");
            return false;
        }
        //En este caso se hizo uso del try-with-resources por lo que no hace falta cerrar los recursos en caso de excepción o éxito
    }
    
    
    
    public boolean guardarOrdenes () throws IOException {
        //Se procede a crear primero el archivo con el nombre solicitado y luego a guardar los objetos como tal porque implementan serializable
        try (FileOutputStream archivoSalida = new FileOutputStream("Ordenes.txt");
                ObjectOutputStream salida = new ObjectOutputStream(archivoSalida)) {
            salida.writeObject(ordenes);
            System.out.println("Ordenes guardadas con éxito");
            return true;
        } catch (SecurityException | FileNotFoundException e) { //Multi-catch de SecurityException en caso de no tener permisos para escribir y FileNotFoundException en caso de que el archivo en el que se escribe no exista
            System.out.println("Ordenes no guardadas");
            return false;
        }
        //En este caso se hizo uso del try-with-resources por lo que no hace falta cerrar los recursos en caso de excepción o éxito
    }
    
    public ArrayList<Producto> cargarMenu () throws IOException, ClassNotFoundException{
        //Se intenta primero leer el archivo
        try (FileInputStream archivoEntrada = new FileInputStream("Menu.txt");
                ObjectInputStream entrada = new ObjectInputStream(archivoEntrada)) {
            System.out.println("Menu cargado con éxito");
            return (ArrayList<Producto>) entrada.readObject();
        } catch (SecurityException | FileNotFoundException e) { //Si no se tiene acceso para lectura o el archivo no existe se crea un arreglo en blanco
            System.out.println("No se cargo el menu");
            return new ArrayList<>();
        }
    }
    
    public ArrayList<Orden> cargarOrdenes () throws IOException, ClassNotFoundException{
        //Se intenta primero leer el archivo
        try (FileInputStream archivoEntrada = new FileInputStream("Ordenes.txt");
                ObjectInputStream entrada = new ObjectInputStream(archivoEntrada)) {
            System.out.println("Ordenes cargadas con éxito");
            return (ArrayList<Orden>) entrada.readObject();
        } catch (SecurityException | FileNotFoundException e) { //Si no se tiene acceso para lectura o el archivo no existe se crea un arreglo en blanco
            System.out.println("No se cargadon las ordenes");
            return new ArrayList<>();
        }
    }
}