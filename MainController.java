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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

/**
 *
 * @author ferhm
 */
public class MainController implements Initializable {
    
    @FXML
    private VBox contentVBox;
    
    private Restaurante LaPodasaDeLosBrujos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        LaPodasaDeLosBrujos = new Restaurante();
        /*Se maneja la integridad del objeto restaurante para que todos los controladores tengan acceso al objeto principal
        Para esto se cargan primero los archivos, de existir
        */
        try {
            LaPodasaDeLosBrujos.setMenu(LaPodasaDeLosBrujos.cargarMenu());
            LaPodasaDeLosBrujos.setOrdenes(LaPodasaDeLosBrujos.cargarOrdenes());
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void cargarVista(String rutaFXML) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
        Parent nuevaVista = loader.load();

        contentVBox.getChildren().setAll(nuevaVista);
        
        //Se maneja la integridad de la referencia a la vista principal
        if (rutaFXML.contains("AgregarProducto.fxml")) {
            AgregarProductoController controller = loader.getController();
            controller.setMainController(this);
            System.out.println("Referencia de MainController pasada a AgregarProductoController");
            controller.setRestaurante(LaPodasaDeLosBrujos);
            System.out.println("Referencia de Restaurante pasada a AgregarProductoController");
        } else if (rutaFXML.contains("ModificarProducto.fxml")) {
            ModificarProductoController controller = loader.getController();
            controller.setMainController(this);
            System.out.println("Referencia de MainController pasada a ModificarProductoController");
            controller.setRestaurante(LaPodasaDeLosBrujos);
            System.out.println("Referencia de Restaurante pasada a ModificarProductoController");
        } else if (rutaFXML.contains("EliminarProducto.fxml")) {
            EliminarProductoController controller = loader.getController();
            controller.setMainController(this);
            System.out.println("Referencia de MainController pasada a EliminarProductoController");
            controller.setRestaurante(LaPodasaDeLosBrujos);
            System.out.println("Referencia de Restaurante pasada a EliminarProductoController");
        } else if (rutaFXML.contains("IngresarOrden.fxml")) {
            IngresarOrdenController controller = loader.getController();
            controller.setMainController(this);
            System.out.println("Referencia de MainController pasada a IngresarOrden");
            controller.setRestaurante(LaPodasaDeLosBrujos);
            System.out.println("Referencia de Restaurante pasada a IngresarOrden");
        } else if (rutaFXML.contains("ReporteCompleto.fxml")) {
            ReporteCompletoController controller = loader.getController();
            controller.setMainController(this);
            System.out.println("Referencia de MainController pasada a ReporteCompleto");
            controller.setRestaurante(LaPodasaDeLosBrujos);
            System.out.println("Referencia de Restaurante pasada a ReporteCompleto");
        } else if (rutaFXML.contains("ReporteFechas.fxml")) {
            ReporteFechasController controller = loader.getController();
            controller.setMainController(this);
            System.out.println("Referencia de MainController pasada a ReporteFechas");
            controller.setRestaurante(LaPodasaDeLosBrujos);
            System.out.println("Referencia de Restaurante pasada a ReporteFechas");
        } else if (rutaFXML.contains("ReporteNumOrd.fxml")) {
            ReporteNumOrdController controller = loader.getController();
            controller.setMainController(this);
            System.out.println("Referencia de MainController pasada a ReporteNumOrd");
            controller.setRestaurante(LaPodasaDeLosBrujos);
            System.out.println("Referencia de Restaurante pasada a ReporteNumOrd");
        }
        
        System.out.println("Vista establecida en contentVBox desde: " + rutaFXML);
    }

    @FXML
    public void mostrarBienvenida() throws IOException {
        cargarVista("/fernandohernandez/proyecto2/vistas/Bienvenida.fxml");
    }
    
    @FXML
    public void mostrarAgregarProducto() throws IOException {
        cargarVista("/fernandohernandez/proyecto2/vistas/AgregarProducto.fxml");
    }
    
    @FXML
    public void mostrarModificarProducto() throws IOException {
        cargarVista("/fernandohernandez/proyecto2/vistas/ModificarProducto.fxml");
    }
    
    @FXML
    public void mostrarEliminarProducto() throws IOException {
        cargarVista("/fernandohernandez/proyecto2/vistas/EliminarProducto.fxml");
    }
    
    @FXML
    public void mostrarIngresarOrden() throws IOException {
        cargarVista("/fernandohernandez/proyecto2/vistas/IngresarOrden.fxml");
    }
    
    @FXML
    public void mostrarReporteCompleto() throws IOException {
        cargarVista("/fernandohernandez/proyecto2/vistas/ReporteCompleto.fxml");
    }
    
    @FXML
    public void mostrarReporteFechas() throws IOException {
        cargarVista("/fernandohernandez/proyecto2/vistas/ReporteFechas.fxml");
    }
    
    @FXML
    public void mostrarReporteNumOrd() throws IOException {
        cargarVista("/fernandohernandez/proyecto2/vistas/ReporteNumOrd.fxml");
    }
    
    public boolean guardarDatos() {
        try {
            System.out.println(LaPodasaDeLosBrujos.getMenu());
            System.out.println(LaPodasaDeLosBrujos.getOrdenes());
            return((LaPodasaDeLosBrujos.guardarMenu()) && (LaPodasaDeLosBrujos.guardarOrdenes()));
            } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}