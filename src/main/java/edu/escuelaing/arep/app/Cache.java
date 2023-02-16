package edu.escuelaing.arep.app;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Almacenar y entrgar las peliculas que se han consultado en el servidor
 */
public class Cache {
    private static Cache instance;

    private static ConcurrentHashMap<String, String> cache;

    /**
     * Constructor de la clase
     */
    public Cache(){
        cache = new ConcurrentHashMap<String, String>();
    }

    /**
     * Saber si un titulo de una pelicula esta incluido en el cache
     * @param title Titulo a consultar
     * @return Retorna el valor de verdad de si la pelicula ya fue consultada
     */
    public boolean isOnCache(String title){
        return cache.containsKey(title);
    }

    /**
     * Añade el titulo de una pelicula y su información al cache
     * @param title Titulo de la pelicula a añadir
     * @param json Información de la pelicula a añadir
     */
    public void addMovie(String title, String json){
        cache.put(title, json);
    }

    /**
     * Obtener la descripción de una pelicula almacenada en el cache basada en su titulo
     * @param title Titulo de la pelicula a consultar
     * @return Informaciuón de la pelicula requerida
     */
    public String getMovieDescription(String title){
        return cache.get(title);
    }

    /**
     * Obtener la unica instancia de cache que existe basado en patron de SINGLETON
     * @return Instancia del Cache
     */
    public static Cache getInstance() {

        if(instance == null){
            instance = new Cache();
        }
        return instance;
    }

    /**
     * Obtener el tamaño del caché almacenado basado en la cantidad de las peliculas almacenadas
     * @return Cantidad de peliculas alamacenadas en el cache
     */
    public int size(){
        return cache.size();
    }

    /**
     * Limpiar al caché y dejarlo sin peliculas en su interior
     */
    public void clear(){cache.clear();}
}
