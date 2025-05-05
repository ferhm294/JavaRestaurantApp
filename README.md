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
Licencia MIT

Derechos de Autor (c) [2024] [Fernando Hernández]

Por la presente se concede permiso, sin cargo, a cualquier persona que obtenga una copia
de este software y archivos de documentación asociados (el "Software"), para tratar
en el Software sin restricciones, incluyendo sin limitación los derechos
para usar, copiar, modificar, fusionar, publicar, distribuir, sublicenciar, y/o vender
copias del Software, y para permitir a las personas a quienes se les proporcione el Software
hacerlo, sujeto a las siguientes condiciones:

El aviso de copyright anterior y este aviso de permiso deberán ser incluidos en todas
las copias o porciones sustanciales del Software.

EL SOFTWARE SE PROPORCIONA "TAL CUAL", SIN GARANTÍA DE NINGÚN TIPO, EXPRESA O
IMPLÍCITA, INCLUYENDO PERO NO LIMITÁNDOSE A LAS GARANTÍAS DE COMERCIALIZACIÓN,
IDONEIDAD PARA UN PROPÓSITO PARTICULAR Y NO INFRACCIÓN. EN NINGÚN CASO LOS
AUTORES O TITULARES DEL COPYRIGHT SERÁN RESPONSABLES DE NINGUNA RECLAMACIÓN, DAÑO U OTRA
RESPONSABILIDAD, YA SEA EN UNA ACCIÓN DE CONTRATO, AGRAVIO O DE OTRO MODO, QUE SURJA DE,
FUERA O EN CONEXIÓN CON EL SOFTWARE O EL USO U OTROS TRATOS EN EL
SOFTWARE.

MIT License

Copyright (c) [2024] [Fernando Hernández]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
