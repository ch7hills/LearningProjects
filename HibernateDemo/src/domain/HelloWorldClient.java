package domain;

import org.hibernate.Session;

import util.HibernateUtil;

public class HelloWorldClient {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Message msg = new Message("Hello World with Hibernate");
		session.save(msg);
		session.getTransaction().commit();
		session.close();
	}
}
