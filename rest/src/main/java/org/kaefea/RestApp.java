package org.kaefea;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.kaefea.rest.TopicController;
import org.kaefea.service.TopicService;

public class RestApp extends ResourceConfig {

    public void ResourceConfig(){
        register(TopicController.class);
        register(TopicService.class);
        register(new RestAppBinder());
        packages(true, "org.kaefea");
        //packages(true, "org.kaefea.service");
    }
}
