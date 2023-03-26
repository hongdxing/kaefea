package org.kaefea;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        try {
            System.out.println("Hello World!");

            KaefeaApp.properties = new Properties();
            KaefeaApp.topics = new HashMap<>();

            // Start rest server
            RestServer restServer = new RestServer();
            restServer.start();

            // Set up ...
            try {
                Object lock = new Object();
                synchronized (lock) {
                    while (true && !KaefeaApp.stopping) {
                        lock.wait();
                    }
                }
            } catch (InterruptedException ex) {
            }
            // Do something after we were interrupted ...
            System.out.println("Kaefea Server stopped");
        }catch (Exception ex){
            System.out.println("Kaefea start failed");
        }
    }


}
