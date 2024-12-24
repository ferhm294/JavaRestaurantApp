/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fernandohernandez.proyecto2.controladores;

import fernandohernandez.proyecto2.modelos.Restaurante;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ferhm
 */
public class ReporteNumOrdController implements Initializable {

    @FXML
    private TextArea txtAReporte;
    
    @FXML
    private TextField entradaNumOrd;
    
    private MainController controladorPrincipal;
    private Restaurante LaPodasaDeLosBrujos;
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtAReporte.setEditable(false);
        txtAReporte.setStyle("-fx-opacity: 1.0; -fx-text-fill: black; -fx-background-color: white;; -fx-font-weight: bold");
    }

    public void setMainController(MainController mainController) {
        this.controladorPrincipal = mainController;
        System.out.println("MainController set en AgregarProductoController");
    }
    
    public void setRestaurante(Restaurante LaPodasaDeLosBrujos) {
        this.LaPodasaDeLosBrujos = LaPodasaDeLosBrujos;
    }
    
    @FXML
    public void mostrarBienvenida() throws IOException {
        controladorPrincipal.mostrarBienvenida();
    }
    
    public void generarReporte() {
        String numOrd = entradaNumOrd.getText();
        System.out.println("Numero de orden ingresada " + numOrd);
        if ("".equals(numOrd)) {
            Alert errorNumOrd = new Alert(Alert.AlertType.ERROR);
            errorNumOrd.setTitle("¡Error!");
            errorNumOrd.setHeaderText("Por favor ingrese el número de orden");
            errorNumOrd.showAndWait();
        } else {
            txtAReporte.setText(LaPodasaDeLosBrujos.reporteNumOrden(numOrd));
        }
    }
}
