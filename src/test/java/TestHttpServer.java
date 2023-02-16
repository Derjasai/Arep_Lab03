import edu.escuelaing.arep.app.Cache;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.HashMap;

public class TestHttpServer extends TestCase {

    public TestHttpServer(String testName){
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TestHttpServer.class );
    }

    /**
     * Test enfocado en buscar los titulos y que cuadren con su respectiva descripción
     */
    public void testSearchTitles()
    {
    }

    /**
     * testea que el caché este guardando correctamente los titulos y que no se este repitiendo el titulo mas de una vez en el caché
     */
    public void testCahceConcurrent(){

    }

    public void testCahceConcurrentPersisten(){


    }
}
