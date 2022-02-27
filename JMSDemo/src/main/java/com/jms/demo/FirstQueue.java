package com.jms.demo;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class FirstQueue {
	public static void testQueue(){
		InitialContext initialContext = null;
		Connection conn =null;
		try {
			Properties props = new Properties();
			props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.artemis.jndi.ActiveMQInitialContextFactory");
			props.setProperty(Context.PROVIDER_URL,"tcp://localhost:61616");
			initialContext = new InitialContext(props);
			//initialContext = new InitialContext();
			ConnectionFactory	cf = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
			conn = cf.createConnection();
			Queue queue = (Queue)initialContext.lookup("queue/myQueue");
			 
			Session session = conn.createSession();
			MessageProducer produvcer = session.createProducer(queue);
			TextMessage message = session.createTextMessage("I am the creator of destiny");
			produvcer.send(message);
			
			//consumer
			MessageConsumer consumer = session.createConsumer(queue);
			conn.start();
			TextMessage msg = (TextMessage) consumer.receive(5000);
			System.out.println("Messgae Received:{}"+ msg.getText());
			
		}catch(NamingException e) {
			e.printStackTrace();
		}catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(initialContext !=null) {
				try {
					initialContext.close();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
