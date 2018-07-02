package com.masinew.database.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.masinew.database.hibernate.entity.ChampHi;
import com.sun.xml.internal.ws.api.policy.SourceModel;

public class AnnotationApp {
	public static void main(String[] a) {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure("annotation.hibernate.cfg.xml")
			.build();
		
		SessionFactory sessionFactory = null; 
		
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}
		catch(Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(new ChampHi("Annotation Saving plan :3"));
		tx.commit();
		session.close();
		
		if (sessionFactory != null) {
			sessionFactory.close();
		}
		
	}
}
