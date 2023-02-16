package edu.escuelaing.arep.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Clase encargada de hacer la consulta hacia las APIS externas
 */
public class APIConnection {

    private static final String USER_AGENT = "Mozilla/5.0";

    /***
     * Realiza la consulta hacia la API externa y toma su JSON para ponerlo en formato de STRING
     * @param title Titulo de la pelicula a consultar en la API externa
     * @param url URL a buscar en el API
     * @return Retorna la descripción de la pelicula en formato de JSON interpretado como STRING
     * @throws IOException Exception
     */
    public static String requestTitle(String title, String url) throws IOException {

        Cache cache = Cache.getInstance();
        if(cache.isOnCache(title)){
            return cache.getMovieDescription(title);
        }

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success)
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            String resp = "["+response.toString()+"]" ;
            cache.addMovie(title, resp);
            return  resp;
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return "Failed";
    }

}
