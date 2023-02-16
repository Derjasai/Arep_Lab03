package edu.escuelaing.arep.app.spark;

@FunctionalInterface
public interface Route {
    Object handle(Request request, Response response);
}
