package edu.escuelaing.arep.app;

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
        HttpServer server = HttpServer.getInstance();
        server.run(args);
    }

}
