/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fernandohernandez.proyecto2.controladores;

import fernandohernandez.proyecto2.modelos.Restaurante;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author ferhm
 */
public class ReporteFechasController implements Initializable {

    @FXML
    private TextArea txtAReporte;
    
    @FXML
    private DatePicker dtPInicio, dtPFinal;
    
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
        LocalDate fechaInicio = dtPInicio.getValue();
        LocalDate fechaFinal = dtPFinal.getValue();
        if (fechaFinal.isBefore(fechaInicio)) {
            Alert errorFecha = new Alert(Alert.AlertType.ERROR);
            errorFecha.setTitle("¡Error!");
            errorFecha.setHeaderText("La fecha final no puede estar antes que la de inicio");
            errorFecha.showAndWait();
        } else {
            txtAReporte.setText(LaPodasaDeLosBrujos.reportePorRangoFechas(fechaInicio, fechaFinal));
        }
    }  
    
}
