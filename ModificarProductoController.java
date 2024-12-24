/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fernandohernandez.proyecto2.controladores;

import fernandohernandez.proyecto2.modelos.PlatoFuerte;
import fernandohernandez.proyecto2.modelos.Postre;
import fernandohernandez.proyecto2.modelos.Producto;
import fernandohernandez.proyecto2.modelos.Restaurante;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author ferhm
 */
public class ModificarProductoController implements Initializable {

    @FXML
    private ComboBox<String>comboGluten, comboTipoPlato, comboCodigo;
    
    @FXML
    private TextField entradaNombre, entradaPorciones, entradaPrecio;
    
    private MainController controladorPrincipal;
    private Restaurante LaPodasaDeLosBrujos;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Inicializando AgregarProductoController");
        // Añadir elementos del combobox de gluten
        ObservableList<String> opcionesGluten = FXCollections.observableArrayList("Sí", "No");
        comboGluten.setItems(opcionesGluten);
        System.out.println("ComboBox de gluten inicializado con: " + comboGluten.getItems());
        
        // Añadir elementos del combobox de tipoplato
        ObservableList<String> opcionesTipoPlato = FXCollections.observableArrayList("Plato Fuerte", "Postre");
        comboTipoPlato.setItems(opcionesTipoPlato);
        System.out.println("ComboBox de gluten inicializado con: " + comboTipoPlato.getItems());
    }
    
    public void setMainController(MainController mainController) {
        this.controladorPrincipal = mainController;
        System.out.println("MainController set en AgregarProductoController");
    }
    
    public void setRestaurante(Restaurante LaPodasaDeLosBrujos) {
        this.LaPodasaDeLosBrujos = LaPodasaDeLosBrujos;
        
        // Añadir elementos del combobox de codigos de producto
        ArrayList<String> cadena = new ArrayList<>();
        ArrayList<Producto> menu = LaPodasaDeLosBrujos.getMenu();
        // Verificar que el menú no sea null
        if (menu == null) {
            System.out.println("No hay productos en el menu");
            Alert alertaNoMenu = new Alert(Alert.AlertType.ERROR);
            alertaNoMenu.setTitle("Error");
            alertaNoMenu.setContentText("No hay elementos en el menu");
            alertaNoMenu.showAndWait();
            // Mostrar la alerta
        } else {
            for (Producto prod : menu) {
                cadena.add(prod.getCodigo() + ", " + prod.getNombre());
            }
            ObservableList<String> opcionesCodigo = FXCollections.observableArrayList(cadena);
            // Verificar que comboCodigo no sea null
            if (comboCodigo == null) {
                System.out.println("comboCodigo es null. Asegúrate de que ha sido inicializado correctamente.");
            } else {
                comboCodigo.setItems(opcionesCodigo); // Usar la lista correcta
                System.out.println("ComboBox de codigos inicializado con: " + comboCodigo.getItems());
            }
        }
    }
    
    @FXML
    public void mostrarBienvenida() throws IOException {
        controladorPrincipal.mostrarBienvenida();
    }
    
    public void cargarDatos() {
        if (comboCodigo.getValue() == null) {
            Alert errorCodigo = new Alert(Alert.AlertType.ERROR);
            errorCodigo.setTitle("Error");
            errorCodigo.setHeaderText("Código inválido");
            errorCodigo.setContentText("Por favor seleccione un código de la lista");
        } else {
            String[] partesCodigo = comboCodigo.getValue().split(","); //Como el combobox también tiene el nombre, ocupamos solo el codigo para hacer la busqueda
            if (LaPodasaDeLosBrujos.buscarProducto(partesCodigo[0]) instanceof PlatoFuerte) {
                PlatoFuerte platoFuerteModificar = (PlatoFuerte) LaPodasaDeLosBrujos.buscarProducto(partesCodigo[0]); //Downcasting para hacer un proceso de modificacion difente para cada uno
                entradaNombre.setText(platoFuerteModificar.getNombre());
                if (platoFuerteModificar.isLibreGluten()) {
                    comboGluten.setValue("No");
                } else {
                    comboGluten.setValue("Sí");
                }
                entradaPorciones.setText(String.valueOf(platoFuerteModificar.getCantidadPorciones()));
                entradaPrecio.setText(String.valueOf(platoFuerteModificar.getPrecio()));
                comboTipoPlato.setValue("Plato Fuerte");
            } else {
                Postre postreModificar = (Postre) LaPodasaDeLosBrujos.buscarProducto(partesCodigo[0]); //Downcasting para hacer un proceso de modificacion difente para cada uno
                entradaNombre.setText(postreModificar.getNombre());
                if (postreModificar.isLibreGluten()) {
                    comboGluten.setValue("No");
                } else {
                    comboGluten.setValue("Sí");
                }
                entradaPorciones.setText(String.valueOf(postreModificar.getCantidadPorciones()));
                entradaPrecio.setText(String.valueOf(postreModificar.getPrecio()));
                comboTipoPlato.setValue("Postre");
            }
        }
    }
    
    public void capturaDatos() {
        String codigo, nombre, tipoSabor;
        boolean libreGluten = false;
        int cantidadPorciones;
        double precio;
        ArrayList<String> ingredientes = new ArrayList<>();
        String[] partesCodigo = comboCodigo.getValue().split(","); //Como el combobox también tiene el nombre, ocupamos solo el codigo para hacer la busqueda
        codigo = partesCodigo[0];
        
        String seleccionCompoTipoPlato = comboTipoPlato.getValue();
        String seleccionComboLibreGluten = comboGluten.getValue();
        
        if (seleccionComboLibreGluten == null) {
            //Retroalimentacion del usuario para que indique si el platillo tiene o no gluten
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("¡Atención!");
            alerta.setHeaderText("Por favor indique si el platillo contiene gluten o no");
            alerta.showAndWait();
        } else if (seleccionCompoTipoPlato == null) {
            //Retroalimentación del usuario para que seleccione el tipo de plato
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("¡Atención!");
            alerta.setHeaderText("Por favor seleccione un tipo de plato");
            alerta.showAndWait();
        } else {
            switch (seleccionCompoTipoPlato) {
                case "Plato Fuerte":
                    System.out.println("Se seleccionó plato fuerte");
                    //Validación del entero de cantidad porciones
                    try {
                        cantidadPorciones = Integer.parseInt(entradaPorciones.getText());

                    } catch (NumberFormatException e) {
                        //Retroalimentación para el usuario para que ponga porciones en numeros enteros
                        Alert alerta = new Alert(Alert.AlertType.WARNING);
                        alerta.setTitle("¡Atención!");
                        alerta.setHeaderText("La porción puede ser solo número entero");
                        alerta.showAndWait();
                        break;
                    }

                    //Validación del precio de cantidad porciones
                    try {
                        precio = Double.parseDouble(entradaPrecio.getText());

                    } catch (NumberFormatException e) {
                        //Retroalimentacion para el usuario para que ponga numeros enteros o con decimales
                        Alert alerta = new Alert(Alert.AlertType.WARNING);
                        alerta.setTitle("¡Atención!");
                        alerta.setHeaderText("El precio puede ser solo números");
                        alerta.showAndWait();
                        break;
                    }

                    //Se valida la selección del gluten para convertirlo a boolean
                    if ("Sí".equals(seleccionComboLibreGluten)) {
                        libreGluten = false;
                    } else if ("No".equals(seleccionComboLibreGluten)) {
                        libreGluten = true;
                    } else if (comboGluten.getValue() == null) {
                        //Retroalimentacion del usuario para que indique si el platillo tiene o no gluten
                        Alert alerta = new Alert(Alert.AlertType.WARNING);
                        alerta.setTitle("¡Atención!");
                        alerta.setHeaderText("Por favor indique si el platillo contiene gluten o no");
                        alerta.showAndWait();
                        break;
                    }
                    nombre = entradaNombre.getText();

                    //Aquí se piden la cantidad de ingredientes con un ciclo
                    // Bucle para ingresar al menos 3 ingredientes
                    int contador = 1, MIN_INGREDIENTES = 3;
                    boolean continuar = true;

                    while (contador <= MIN_INGREDIENTES || continuar) {
                        TextInputDialog entradaIngredientes = new TextInputDialog();
                        entradaIngredientes.setTitle("Ingrese los ingredientes");
                        entradaIngredientes.setHeaderText("Ingredientes del plato fuerte. Si no desea agregar más ingredientes presione cancelar.");
                        entradaIngredientes.setContentText("Ingrediente " + contador);

                        Optional<String> resultado = entradaIngredientes.showAndWait();
                        if (resultado.isPresent() && !resultado.get().trim().isEmpty()) {
                            ingredientes.add(resultado.get());
                            System.out.println("El usuario ingresó: " + resultado.get());
                            contador++;
                        } else {
                            // Si el usuario cancela o no ingresa nada, salir del bucle solo si ya ingresó al menos 3 ingredientes
                            if (contador > MIN_INGREDIENTES) {
                                continuar = false;
                            } else {
                                //Retroalimentacion para el usuario para que ponga numeros enteros o con decimales
                                Alert errorIngredientes = new Alert(Alert.AlertType.WARNING);
                                errorIngredientes.setTitle("¡Atención!");
                                errorIngredientes.setHeaderText("Debe ingresar al menos " + MIN_INGREDIENTES + " ingredientes.");
                                errorIngredientes.showAndWait();
                                System.out.println("Debe ingresar al menos " + MIN_INGREDIENTES + " ingredientes.");

                            }
                        }
                    }

                    // Imprimir los ingredientes capturados
                    System.out.println("Ingredientes capturados: " + ingredientes);

                    //Se procede a modificar el postre
                    if (LaPodasaDeLosBrujos.modificarPlatoFuerte(codigo, nombre, libreGluten, cantidadPorciones, precio, ingredientes)) {
                        System.out.println("Se modificó el plato fuerte");
                        //Se informa al usuario que el postre se guardó exitosamente
                        Alert exito = new Alert(Alert.AlertType.INFORMATION);
                        exito.setTitle("¡Éxito!");
                        exito.setHeaderText("Plato fuerte modificado exitosamente");
                        exito.showAndWait();
                    } else {
                        System.out.println("Error modificando plato fuerte");
                        //Se informa al usuario que el postre no se modificó exitosamente
                        Alert exito = new Alert(Alert.AlertType.ERROR);
                        exito.setTitle("¡Error!");
                        exito.setHeaderText("Error modificando plato fuerte");
                        exito.showAndWait();
                    }
                    limpiarCampos();
                    break;
                case "Postre":
                    System.out.println("Se seleccionó postre");
                    //Se crea un choice dialog para que elija el sabor del postre
                    ArrayList<String> opciones = new ArrayList<>();
                    opciones.add("Dulce");
                    opciones.add("Salado");
                    opciones.add("Amargo");
                    ChoiceDialog<String> opcionesSabor = new ChoiceDialog<>(opciones.get(0), opciones);
                    opcionesSabor.setTitle("Selecciones una Opción");
                    opcionesSabor.setHeaderText("¿Qué sabor tiene este postre?");
                    opcionesSabor.setContentText("Opciones");

                    Optional<String> sabor = opcionesSabor.showAndWait();
                    System.out.println("El postre es " + sabor.get());
                    tipoSabor = sabor.get();

                    //Validación del entero de cantidad porciones
                    try {
                        cantidadPorciones = Integer.parseInt(entradaPorciones.getText());

                    } catch (NumberFormatException e) {
                        //Retroalimentación para el usuario para que ponga porciones en numeros enteros
                        Alert errorPorcion = new Alert(Alert.AlertType.WARNING);
                        errorPorcion.setTitle("¡Atención!");
                        errorPorcion.setHeaderText("La porción puede ser solo número entero");
                        errorPorcion.showAndWait();
                        break;
                    }

                    //Validación del precio de cantidad porciones
                    try {
                        precio = Double.parseDouble(entradaPrecio.getText());

                    } catch (NumberFormatException e) {
                        //Retroalimentacion para el usuario para que ponga numeros enteros o con decimales
                        Alert errorPrecio = new Alert(Alert.AlertType.WARNING);
                        errorPrecio.setTitle("¡Atención!");
                        errorPrecio.setHeaderText("El precio puede ser solo números");
                        errorPrecio.showAndWait();
                        break;
                    }

                    //Se valida la selección del gluten para convertirlo a boolean
                    if ("Sí".equals(seleccionComboLibreGluten)) {
                        libreGluten = true;
                    } else if ("No".equals(seleccionComboLibreGluten)) {
                        libreGluten = false;
                    } else if (comboGluten.getValue() == null) {
                        //Retroalimentacion del usuario para que indique si el platillo tiene o no gluten
                        Alert errorGluten = new Alert(Alert.AlertType.WARNING);
                        errorGluten.setTitle("¡Atención!");
                        errorGluten.setHeaderText("Por favor indique si el platillo contiene gluten o no");
                        errorGluten.showAndWait();
                        break;
                    }
                    nombre = entradaNombre.getText();

                    //Se procede a modificar el postre
                    if (LaPodasaDeLosBrujos.modificarPostre(codigo, nombre, libreGluten, cantidadPorciones, precio, tipoSabor)) {
                        System.out.println("Se modificó el postre");
                        //Se informa al usuario que el postre se guardó exitosamente
                        Alert exito = new Alert(Alert.AlertType.INFORMATION);
                        exito.setTitle("¡Éxito!");
                        exito.setHeaderText("Postre modificado exitosamente");
                        exito.showAndWait();
                    } else {
                        System.out.println("Error modificando postre");
                        //Se informa al usuario que el postre no se modificó exitosamente
                        Alert exito = new Alert(Alert.AlertType.ERROR);
                        exito.setTitle("¡Error!");
                        exito.setHeaderText("Error modificando postre");
                        exito.showAndWait();
                    }

                    limpiarCampos();
                    break;
            }
        }
    }
    
    public void limpiarCampos() {
        //Se limpian los campos para una próxima agregación
        comboCodigo.setValue(null);
        entradaNombre.setText("");
        comboGluten.setValue(null);
        entradaPorciones.setText("");
        entradaPrecio.setText("");
        comboTipoPlato.setValue(null);
    }
}
