# AREP Taller 2

Aplicación web la cual incluye HTML, CSS y JavaScrpit para mostrar un servicio web mediante el pedido de servicios.

## Iniciando

### Prerequisites

- Maven - Administrador de dependencias y administrador del ciclo de vida del proyecto
- Java - Ambiente de desarrollo
-  Git - Sistema de control de versiones y descarga del repositorio

### Instalando el entorno

Para correr el programa primero descargue el repositorio con el siguiente comando
```
git clone https://github.com/Derjasai/AREP_Lab02.git
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

Se hace una arquitectura enfocada en API Rest. Se implementa el patrón de diseño SINGLETON para la creación de caché, puesto que este es el único caché que debe existir dentro del servidor

- Extensibilidad: Se hace una sola clase para el servicio de páginas web llamada PagesServices, la cual usa el patrón de diseño Singleton, con esto solo necesitamos pasarle el path y una extensión para poder visualizar un recurso que se tenga almacenado en disco, en lugar de crear un servicio por cada recurso que se quiera visualizar 
- Patrones usados: Se usa el patrón de Fachada y el patrón de Singleton
- Modularización: Todas clases implementan metodos los cuales singuel el principio de unica responsabilidad, lo cual nos permite extender el codigo de ser necesario en dado caso que se necesite