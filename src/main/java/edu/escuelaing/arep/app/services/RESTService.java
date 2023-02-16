package edu.escuelaing.arep.app.services;

/**
 * Interfaz que implementa el diseño de un servicio rest
 */
public interface RESTService {
    /**
     * Obtener el Header de un recurso solicitado
     * @param type el tipo o extensión del recurso
     * @param code Codigo de HTTP
     * @return EL header del recurso
     */
    public String getHeader(String type, String code);

    /**
     * Obtener el contenido o cuerpo del recurso solicitado
     * @param path El path o dirección de donde se encuentra el recurso
     * @return El contenido del recurso
     */
    public String getResponse(String path);
}
