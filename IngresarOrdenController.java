/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fernandohernandez.proyecto2.controladores;

import fernandohernandez.proyecto2.modelos.Detalle;
import fernandohernandez.proyecto2.modelos.Orden;
import fernandohernandez.proyecto2.modelos.Producto;
import fernandohernandez.proyecto2.modelos.Restaurante;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author ferhm
 */
public class IngresarOrdenController implements Initializable {

    @FXML
    private TextField campoNumOrd, FechaOrden, entradaCliente;
    
    private MainController controladorPrincipal;
    private Restaurante LaPodasaDeLosBrujos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Inicializando IngresarOrdenController");
        FechaOrden.setText(LocalDate.now().toString());
    }
    
    public void setMainController(MainController mainController) {
        this.controladorPrincipal = mainController;
        System.out.println("MainController set en AgregarProductoController");
    }
    
    public void setRestaurante(Restaurante LaPodasaDeLosBrujos) {
        this.LaPodasaDeLosBrujos = LaPodasaDeLosBrujos;
        campoNumOrd.setText(LaPodasaDeLosBrujos.generarNumOrd());
    }
    
    public void crearOrden() {
    String numOrd = campoNumOrd.getText(), cliente = entradaCliente.getText();
    Orden nuevaOrden = new Orden(numOrd, cliente);
    boolean banderaMenu = true;
    
    //Se reciben los datos de detalles por pop ups
    boolean continuarDetalles = true, continuarCantidad = true;
    do {
        //Carga productos para las opciones
        ArrayList<String> productos = new ArrayList<>();
        int cantidadAgregar = 1;
        for (Producto prod : LaPodasaDeLosBrujos.getMenu()) {
            productos.add(prod.getCodigo() + ", " + prod.getNombre());
        }
        
        if (!productos.isEmpty() && LaPodasaDeLosBrujos.isMenuValid()) {
            //Se crea el cuadro de diálogo
            ChoiceDialog<String> opcionesProductos = new ChoiceDialog<>(productos.get(0), productos);
            opcionesProductos.setTitle("Selecciones una Opción");
            opcionesProductos.setHeaderText("¿Qué producto desea agregar?");
            opcionesProductos.setContentText("Opciones");

            //Se guarda la respuesta del usuario
            Optional<String> opctionalProductoAgregar = opcionesProductos.showAndWait();
            if (opctionalProductoAgregar.isPresent()) {
                String[] partesProducto = opctionalProductoAgregar.get().split(",");

                do {
                    //Validar que se reciba entero en cantidad
                    try {
                        TextInputDialog entradaCantidad = new TextInputDialog();
                        entradaCantidad.setTitle("Cantidad");
                        entradaCantidad.setHeaderText("¿Cuánto desea agregar a la orden?");
                        entradaCantidad.setContentText("Digite solo números enteros: ");
                        Optional<String> cantidad = entradaCantidad.showAndWait();
                        cantidadAgregar = Integer.parseInt(cantidad.get());
                        continuarCantidad = false;
                    } catch (NumberFormatException e) {
                        Alert errorCantidad = new Alert(Alert.AlertType.ERROR);
                        errorCantidad.setTitle("¡Atención!");
                        errorCantidad.setHeaderText("La cantidad puede ser solo número entero");
                        errorCantidad.showAndWait();
                        continuarCantidad = true;
                    }
                } while (continuarCantidad);
                
                Producto productoAgregar = LaPodasaDeLosBrujos.buscarProducto(partesProducto[0]);
                Detalle nuevoDetalle = new Detalle(productoAgregar, cantidadAgregar);
                nuevaOrden.agregarDetalle(nuevoDetalle);

                //Confirmamos con el usuario si quiere agregar otro detalle
                Alert confirmarContinuarDetalles = new Alert(Alert.AlertType.CONFIRMATION);
                confirmarContinuarDetalles.setTitle("¿Continuar?");
                confirmarContinuarDetalles.setHeaderText("¿Desea agregar otro detalle?");
                Optional<ButtonType> resultado = confirmarContinuarDetalles.showAndWait();
                continuarDetalles = resultado.isPresent() && resultado.get() == ButtonType.OK;
            }
        } else {
            Alert sinProductos = new Alert(Alert.AlertType.ERROR);
            sinProductos.setTitle("¡Error!");
            sinProductos.setHeaderText("No hay productos suficientes en el menu.\nAsegúrate de registrar por lo menos un plato fuerte y un postre");
            sinProductos.showAndWait();
            banderaMenu = false;
            continuarDetalles = false;
        }
    } while (continuarDetalles);

    //Se procede con la entrada de descuento si aplica
    if (nuevaOrden.aplicaDescuento() && banderaMenu) {
        ArrayList<String> codigosDescuento = new ArrayList<>();
        codigosDescuento.add("0");
        codigosDescuento.add("DESC5");
        codigosDescuento.add("DESC10");
        codigosDescuento.add("DESC15");
        ChoiceDialog<String> opcionCodigoDescuento = new ChoiceDialog<>(codigosDescuento.get(0), codigosDescuento);
        opcionCodigoDescuento.setTitle("Selecciones una Opción");
        opcionCodigoDescuento.setHeaderText("¿Desea aplicar algún codigo de descuento?");
        opcionCodigoDescuento.setContentText("Opciones");

        Optional<String> descuentoAgregar = opcionCodigoDescuento.showAndWait();
        nuevaOrden.setCodigoDescuento(descuentoAgregar.get());
        nuevaOrden.calcularTotal();
    } else if (banderaMenu) {
        nuevaOrden.calcularTotal();
    }
    
    if (banderaMenu && LaPodasaDeLosBrujos.isMenuValid()) {
        LaPodasaDeLosBrujos.agregarOrden(nuevaOrden);
        Alert exitoAgregarOrden = new Alert(Alert.AlertType.INFORMATION);
        exitoAgregarOrden.setTitle("¡Éxito!");
        exitoAgregarOrden.setHeaderText("Orden guardada con éxito: " + nuevaOrden.toString());
        exitoAgregarOrden.showAndWait();
        limpiarCampos();
    }
}
    
    public void limpiarCampos() {
        entradaCliente.setText("");
        campoNumOrd.setText(LaPodasaDeLosBrujos.generarNumOrd());
    }
    
    @FXML
    public void mostrarBienvenida() throws IOException {
        controladorPrincipal.mostrarBienvenida();
    }
}