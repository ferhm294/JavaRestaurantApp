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
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author ferhm
 */
public class ReporteCompletoController implements Initializable {

    
    @FXML
    private TextArea txtAReporte;
    
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
        txtAReporte.setText(LaPodasaDeLosBrujos.reporteOrdenes());
    }
}
