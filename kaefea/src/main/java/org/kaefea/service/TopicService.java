/**
 * author: HongXing
 * date: 25-Mar-2023
 */

package org.kaefea.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.kaefea.KaefeaApp;
import org.kaefea.constant.TopicConst;
import org.kaefea.model.Message;
import org.kaefea.model.OperateResult;
import org.kaefea.App;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class TopicService {


	public OperateResult createTopic(String topicName) {

		try {
			
			if(KaefeaApp.topics.containsKey(topicName)) {
				return new OperateResult(false, String.format("Topic {} exist already", topicName));
			}
			
			String dataDir = (String) KaefeaApp.properties.get("data.dir");
			if (!dataDir.endsWith(File.pathSeparator)) {
				dataDir = dataDir + File.pathSeparator;
			}

			Path path = Paths.get(dataDir + TopicConst.TOPIC_FOLDER);
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
	
	
	
	
	
	

}
