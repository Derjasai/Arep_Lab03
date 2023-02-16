package edu.escuelaing.arep.app.spark;

public class Spark {

    private static Spark instance;

    private Spark(){}

    public static Spark getInstance() {
        if(instance == null){
            instance = new Spark();
        }
        return instance;
    }

    public static void get(String path, Route route){

    }

}
