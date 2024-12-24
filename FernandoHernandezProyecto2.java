/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fernandohernandez.proyecto2;

import fernandohernandez.proyecto2.controladores.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ferhm
 */
public class FernandoHernandezProyecto2 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fernandohernandez/proyecto2/vistas/MainView.fxml"));
        Parent root = loader.load();
        //Obtenemos el controlador de la vista principal
        MainController controlador = loader.getController();
        
        //Se inicializa la pantalla de bienvenida
        controlador.mostrarBienvenida();
        
        Scene scene = new Scene(root);
        stage.setTitle("La Posada de los Brujos");
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest((event) -> {
            try {
                if (controlador.guardarDatos()) {
                    System.out.println("Guardar al cerrar con éxito");
                } else {
                    System.out.println("No se guardó al cerrar");
                }
            } catch (Exception e) {
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}