package edu.escuelaing.arep.app.spark;

import java.util.HashMap;

public class Spark {

    private static Spark instance;

    private HashMap<String, Route> services = new HashMap<>();

    private Spark(){}

    public static Spark getInstance() {
        if(instance == null){
            instance = new Spark();
        }
        return instance;
    }

    public void get(String path, Route route){
        services.put(path, route);
    }
    public HashMap<String, Route> getSerivices(){
        return services;
    }

}
