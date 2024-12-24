# JavaRestaurantApp

## Descripción
Proyecto Java para la administración del menú y órdenes de un restaurante. Incluye funcionalidades para gestionar platos principales y postres, crear y seguir órdenes, con persistencia de datos a través de archivos TXT serializados. También ofrece reportes in app para las órdenes del restaurante. Ideal para aprender y practicar habilidades en Java.

## Tecnologías Utilizadas
- **Java:** Lenguaje principal utilizado para desarrollar la aplicación.
- **JavaFX:** Para la interfaz gráfica de usuario.
- **Archivos TXT serializados:** Para la persistencia de datos.

## Funcionalidades
- Gestión de platos principales y postres del menú.
- Creación y seguimiento de órdenes.
- Reportes in app para las órdenes.

## Estructura del Proyecto
El proyecto está organizado siguiendo el patrón MVC (Model-View-Controller):

- **Controladores:** Manejan la lógica de la aplicación y las interacciones del usuario.
- **Modelos:** Representan los datos y la lógica del negocio.
- **Vistas:** Encargadas de la presentación y la interfaz gráfica de usuario.

Las diferentes carpetas están distribuidas en ramas para una mejor organización:
- **main:** Contiene el archivo principal de Java que levanta la aplicación.
- **vistas:** Contiene los archivos FXML para las vistas.
- **modelos:** Contiene los archivos .java para los modelos.
- **controladores:** Contiene los archivos .java para los controladores.

## Instrucciones de Instalación
1. Clona el repositorio: `git clone https://github.com/ferhm294/JavaRestaurantApp`
2. Importa el proyecto en tu IDE favorito.
3. Compila y ejecuta la aplicación.

## Uso
Para utilizar la aplicación, sigue estos pasos:
1. Inicia la aplicación desde el archivo principal en la rama `main`.
2. Añade al menos un plato principal y un postre al menú antes de intentar agregar órdenes.
3. Navega a través de las diferentes funcionalidades para gestionar platos y órdenes.
4. Al crear una orden, si el total de la orden es mayor a 10000, se habilita la opción para agregar un código de descuento.
5. Genera reportes in app para las órdenes del restaurante.

## Autor
Fernando Hernandez

## Licencia
Este proyecto está licenciado bajo los términos de la [MIT License](LICENSE).
