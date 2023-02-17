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
        spark.get("/", ((request, response) -> {
            response.setType("application/json");
            response.setCode("200 OK");
            response.setPath("test.json");
            response.setBody();
            return response.getResponse();
        }));

        HttpServer server = HttpServer.getInstance();
        server.run(args);
    }

}
