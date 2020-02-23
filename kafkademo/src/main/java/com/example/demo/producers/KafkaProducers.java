package com.example.demo.producers;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
@Service
public class KafkaProducers {
	private static Logger log = LoggerFactory.getLogger(KafkaProducers.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${go.kafkademo.topic}")
	private String kafkaDemoTopic;
	
	public void postComment(String message) throws InterruptedException, ExecutionException, TimeoutException, Exception {
		//try {
			System.out.println("In Producer ->"+message);
			System.out.println(kafkaTemplate+" In kafkaDemoTopic ->"+kafkaDemoTopic);
			SendResult<String, String> sendResult = kafkaTemplate.send(kafkaDemoTopic, message).get(5,TimeUnit.SECONDS);
			System.out.println("------------------------->");
		/*} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception");
			System.out.println(e);
			log.debug("exception :->",e);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception");
			System.out.println(e);
			log.debug("exception :->",e);
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception");
			System.out.println(e);
			log.debug("exception :->",e);
		}catch(Exception e) {
			System.out.println("Exception");
			System.out.println(e);
			log.debug("exception :->",e);
		}
*/	}
}
