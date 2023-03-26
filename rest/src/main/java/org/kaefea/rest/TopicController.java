package org.kaefea.rest;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;

@Path(value = "/topics")
@Slf4j
public class TopicController {

    @POST()
    public void createTopic(){

    }


    @GET
    public String getTopic(){
        return "this is a topic";
    }

}
