package edu.escuelaing.arep.app.services;

import java.io.IOException;
import java.nio.file.*;

/**
 * Obtener los recursos necesarios para un servicio web
 */
public class PagesService implements RESTService{

    public static PagesService instance;

    /**
     * Constructor
     */
    private PagesService(){}

    /**
     * Obtener la instancia de la clase segun el patrón de Singleton
     * @return La única instancia de la clase
     */
    public static PagesService getInstance(){
        if(instance == null){
            instance = new PagesService();
        }
        return instance;
    }

    @Override
    public String getHeader(String type, String code) {
        return "HTTP/1.1 "+code+"\r\n" +
                "Content-type: text/"+type+"\r\n" +
                "\r\n";
    }

    @Override
    public String getResponse(String path) {
        byte[] fileContent;
        try {
            fileContent = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String(fileContent);
    }
}
