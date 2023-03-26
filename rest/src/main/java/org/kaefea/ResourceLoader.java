package org.kaefea;

import jakarta.ws.rs.core.Application;
import org.kaefea.rest.TopicController;

import java.util.HashSet;
import java.util.Set;

public class ResourceLoader extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();

        classes.add(TopicController.class);
        return classes;
    }
}
