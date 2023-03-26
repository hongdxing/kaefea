package org.kaefea;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.kaefea.rest.TopicController;
import org.kaefea.service.TopicService;

public class RestAppBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(TopicController.class).to(TopicController.class);
        bind(TopicService.class).to(TopicService.class);
    }
}
