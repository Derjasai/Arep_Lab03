package edu.escuelaing.arep.app;

import java.net.*;
import java.io.*;
import java.util.HashMap;

import edu.escuelaing.arep.app.services.PagesService;
import org.json.*;

/**
 * Levantar un servicio web que va a correr por el puerto 35000
 */
public class HttpServer {

    private static HttpServer instance;

    public static HttpServer getInstance() {
        if (instance == null){
            instance = new HttpServer();
        }
        return instance;
    }

    /**
     * Metodo para iniciar el programa
     * @param args main
     * @throws IOException exception
     */
    public void run (String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while(running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine, title="";

            boolean first_line = true;
            String request = "/simple";

            while ((inputLine = in.readLine()) != null) {
                if(inputLine.contains("info?title=")){
                    String[] prov = inputLine.split("title=");
                    title = (prov[1].split("HTTP")[0]).replace(" ", "");
                }
                if (first_line) {
                    request = inputLine.split(" ")[1];
                    first_line = false;
                }
                //System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            if (request.startsWith("/apps/")) {
                outputLine = executeService(request.substring(5));
                //outputLine = jsonSimple();
            }
            else if(!title.equals("")){
                String response = APIConnection.requestTitle(title, "http://www.omdbapi.com/?t="+title+"&apikey=7ca9f0c2");
                outputLine ="HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<br>"
                        + "<table border=\" 1 \"> \n " + doTable(response)+

                        "    </table>";
            }else {
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + index();
            }
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * Ejecutar un servicio indicado por el path /apps/
     * @param serviceName El nombre del servicio o recurso a ejecutar
     * @return EL Header y Body del recurso solicitado, en caso de no encontrarse el recurso se le dirigirá a un 404
     */
    private String executeService(String serviceName) {
        PagesService ps = PagesService.getInstance();
        try {
            String type = serviceName.split("\\.")[1];
            String header = ps.getHeader(type, "200 OK");
            String body = ps.getResponse("src/main/resources/" + serviceName);
            return header + body;
        }
        catch (RuntimeException e){
            String header = ps.getHeader("html", "404 Not Found");
            String body = ps.getResponse("src/main/resources/404.html");
            return header + body;
        }
    }

    /**
     * Formar el contenido de una tabla basado en un String que se le pase con el formato de un archivo JSON
     * @param response Archivo en formato JSON a transformar en tabla
     * @return String del contenido de una tabla formada en HTML
     */
    private static String doTable(String response){
        HashMap<String,String> dict = new HashMap<String, String>();
        JSONArray jsonArray = new JSONArray(response);
        for (int i=0; i<jsonArray.length();i++){
            JSONObject object = jsonArray.getJSONObject(i);
            for (String key: object.keySet()) {
                dict.put(key.toString(), object.get(key).toString());
            }
            //System.out.println(i+" "+object.toString());
        }

        String table = "<tr> \n";
        for (String keys: dict.keySet()){
            String value = dict.get(keys);
            table += "<td>" + keys + "</td>\n";
            table += "<td>" + value + "</td>\n";
            table += "<tr> \n";
        }
        return table;
    }

    /**
     * Entregar el index de la página principal
     * @return index en formato de String del HTML del inicio de la Página
     */
    private static String index(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Buscador de peliculas</title>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Buscar una pelicula</h1>\n" +
                "<form action=\"/hello\">\n" +
                "    <label for=\"name\">Titulo de la pelicula a buscar:</label><br>\n" +
                "    <input type=\"text\" id=\"name\" name=\"name\" value=\"The Avengers\"><br><br>\n" +
                "    <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "</form>\n" +
                "<div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "<script>\n" +
                "            function loadGetMsg() {\n" +
                "                let nameVar = document.getElementById(\"name\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/info?title=\"+nameVar);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script>\n" +
                "</body>\n" +
                "</html>";
    }
}