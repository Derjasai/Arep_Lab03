package edu.escuelaing.arep.app.spark;

@FunctionalInterface
public interface Route {
    String handle(Request request, Response response);
}
