package com.demo.JmsDemo1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class FirstQueue {
	public static void main(String[] args) {
		InitialContext initialContext = null;
		Connection conn =null;
		try {
			initialContext = new InitialContext();
			ConnectionFactory	cf = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
			Queue queue = (Queue)initialContext.lookup("queue/myQueue");
			 conn = cf.createConnection();
			Session session = conn.createSession();
			MessageProducer produvcer = session.createProducer(queue);
			TextMessage message = session.createTextMessage("I am the creator of destiny");
			produvcer.send(message);
			
			//consumer
			MessageConsumer consumer = session.createConsumer(queue);
			conn.start();
			Message msg = consumer.receive(5000);
			System.out.println("Messgae Received:{}"+ msg);
			
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
