package edu.escuelaing.arep.app;

import edu.escuelaing.arep.app.spark.Spark;

import java.io.IOException;

/**
 * Clase para correr el servidor HttpServer
 */
public class App {

    /**
     * Iniciar el programa
     * @param args args
     * @throws IOException Por si algo sale mal en el proceso
     */
    public static void main(String[] args) throws IOException {
        Spark spark = Spark.getInstance();
        spark.get("/", ((request, response) -> {return "HTTP/1.1 200 \r\n" +
                "Content-type: text/html \r\n" +
                "\r\n" + "Hola";}));

        HttpServer server = HttpServer.getInstance();
        server.run(args);
    }

}
