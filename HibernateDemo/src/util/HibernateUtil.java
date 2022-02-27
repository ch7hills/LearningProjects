package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory= buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
			  // for Hibernate 4.3.x users
            // Create the SessionFactory from hibernate.cfg.xml 
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");     
            return configuration.buildSessionFactory( new StandardServiceRegistryBuilder().applySettings( configuration.getProperties() ).build() );

		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
