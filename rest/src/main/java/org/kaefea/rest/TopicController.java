package org.kaefea.rest;


import jakarta.annotation.ManagedBean;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.kaefea.KaefeaApp;
import org.kaefea.constant.TopicConst;
import org.kaefea.model.Message;
import org.kaefea.model.OperateResult;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@Path(value = "/topics")
@Slf4j
public class TopicController {


    @POST()
    @Produces(MediaType.APPLICATION_JSON)
    public OperateResult createTopic( String topicName){
        try {

            if(KaefeaApp.topics.containsKey(topicName)) {
                return new OperateResult(false, String.format("Topic {} exist already", topicName));
            }

            String dataDir = (String) KaefeaApp.properties.get("data.dir");
            if (!dataDir.endsWith(File.pathSeparator)) {
                dataDir = dataDir + File.pathSeparator;
            }

            java.nio.file.Path path = Paths.get(dataDir + TopicConst.TOPIC_FOLDER);
            if (Files.notExists(path)) {
                return new OperateResult(false, String.format("data.dir: {} Not exists", dataDir));
            } else {
                try {
                    Files.createDirectories(path);
                } catch (Exception ex) {
                    return new OperateResult(false, "Can not create topic folder");
                }
            }

            // create topic in memory
            // create file topicName under topics folder
            synchronized(this) {
                KaefeaApp.topics.put(topicName, new ArrayList<Message>());
                path = Paths.get(dataDir + TopicConst.TOPIC_FOLDER + File.pathSeparator + topicName);
                Files.createFile(path);
            }

            return new OperateResult(true, "OK");
        } catch (Exception ex) {
            return new OperateResult(false, "Create topic failed");
        }
    }


    @GET
    public String getTopic(){
        return "this is a topic";
    }

}
