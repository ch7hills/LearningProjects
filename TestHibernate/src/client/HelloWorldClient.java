package client;

import org.hibernate.Session;

import entity.Message;
import util.HibernateUtil;

public class HelloWorldClient {
	
	public static void main(String[] args) {
		try {
		
				Session session = HibernateUtil.getSessionFactory().openSession();
        		session.beginTransaction();
        
        		Message message = new Message( "Hello World with Hibernate and JPA Annotations" );
        
        		session.save(message);    
        
        		session.getTransaction().commit();
        		session.close();
		}catch(Exception e) {
			System.out.println("Exception==================>");
			e.printStackTrace();
		}
	}
}