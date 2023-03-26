package org.kaefea;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * Hello world!
 *
 */
@Slf4j
public class App
{
    public static void main(String[] args )
    {
        try {
            System.out.println("Hello World!");

            KaefeaApp.properties = new Properties();
            KaefeaApp.topics = new HashMap<>();

            try (InputStream input = new FileInputStream("./kaefea.properties")) {

                Properties prop = new Properties();
                prop.load(input);

                log.info(prop.getProperty("data.dir"));

            } catch (IOException ex) {
                ex.printStackTrace();
            }

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
