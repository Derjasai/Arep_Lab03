# AREP Taller 3

Aplicación que permite construir aplicaciones web mediante el uso de funciones LAMBDA, la cual permite al usuario buscar archivos estáticos en un directorio especificado por él

## Iniciando

### Prerequisites

- Maven - Administrador de dependencias y administrador del ciclo de vida del proyecto
- Java - Ambiente de desarrollo
-  Git - Sistema de control de versiones y descarga del repositorio

### Instalando el entorno

Para correr el programa primero descargue el repositorio con el siguiente comando
```
git clone https://github.com/Derjasai/AREP_Lab03.git
```

Una vez clonado el repositorio ingrese en la carpeta descargada y corra el siguiente comando para ejecutar el programa

```
mvn clean package exec:java -D"exec.mainClass"="edu.escuelaing.arep.app.App"
```

Finalmente ingrese al navegador de su preferencia con el siguiente link:  http://localhost:35000/apps/index.html

En este caso se verá la página html que fue creada, en caso de buscar un servicio que no existe en el momento se le dirigirá a una página de error 404, por ejemplo usando el siguiente link:  
http://localhost:35000/apps/a.html

Si desea ver como tal un archivo deseado, como por ejemplo solo el css, ingrese al siguiente link:  
http://localhost:35000/apps/index.css

## Pruebas realizadas

Funcionamiento del post:
![img.png](imgs/img.png)
Como nos podemos dar cuenta al momento de realizar el post nuestra respuesta es un JSON, así mismo recibimos el código 201 el cual hace referencia a que la creación fue exitosa

Funcionamiento del get:
![img_1.png](imgs/img_1.png)
Al momento de hacer el get nos fijamos que nos obtiene el html que tenemos guardado con el nombre de index.html, en caso de que el usuario indique un recurso que no existe en este momento lo dirigiremos a la siguiente pantalla:
![img_2.png](imgs/img_2.png)  
Como podemos fijarnos mediante postman podemos ver el código de error que nos genera al tener buscar un recurso que no existe
![img_3.png](imgs/img_3.png)

## Documentación

Se puede encontrar la documentación en la carpeta nombrada "javadoc", para generar nueva documentación puede correr el siguiente comando
```
mvn javadoc:javadoc
```
La nueva documentación generada puede encontrarla en la ruta /target/site/apidocs

## Construido con

* [Maven](https://maven.apache.org/) - Dependency Management

## Versonamiento

Versión 1.0

## Autores

* Daniel Esteban Ramos Jimenéz

## Explicaciones tecnicas

Se hace una arquitectura enfocada en API Rest. Se implementa el patrón de diseño SINGLETON para la creación del microframework de spark, de esta forma solo necesitamos un spark el cual nos gestiona todos los métodos que el tiene y puede usar.

- Extensibilidad: El usuario puede buscar cualquier tipo de archivo que exista dentro de resources, incluyendo carpetas o directorios internos. El uso de funciones lambda nos permite hacer la creación y uso de diferentes servicios.
- Extensibilidad: Se hace una sola clase para el servicio de páginas web llamada PagesServices, la cual usa el patrón de diseño Singleton, con esto solo necesitamos pasarle el path y una extensión para poder visualizar un recurso que se tenga almacenado en disco, en lugar de crear un servicio por cada recurso que se quiera visualizar 
- Patrones usados: Se usa el patrón de Fachada y el patrón de Singleton
- Modularización: Todas clases implementan metodos los cuales singuel el principio de unica responsabilidad, lo cual nos permite extender el codigo de ser necesario en dado caso que se necesite