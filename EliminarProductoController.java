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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ferhm
 */
public class EliminarProductoController implements Initializable {

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
        refrescarCodigos();
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
    
    public void refrescarCodigos() {
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
    
    public void eliminarProducto() {
        String[] partesCodigo = comboCodigo.getValue().split(","); //Como el combobox también tiene el nombre, ocupamos solo el codigo para hacer la busqueda
        boolean bandera = LaPodasaDeLosBrujos.eliminarProducto(partesCodigo[0]);
        if (bandera) {
            System.out.println("Producto "+ partesCodigo + " eliminado exitosamente");
            Alert exitoEliminar = new Alert(Alert.AlertType.INFORMATION);
            exitoEliminar.setTitle("Éxito");
            exitoEliminar.setHeaderText("Producto eliminado con éxito.");
            exitoEliminar.showAndWait();
            refrescarCodigos();
        } else {
            System.out.println("Error al eliminar producto");
            Alert errorEliminar = new Alert(Alert.AlertType.ERROR);
            errorEliminar.setTitle("Error");
            errorEliminar.setHeaderText("Error eliminando producto.");
            errorEliminar.showAndWait();
        }
        limpiarCampos();
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