package com.masinew.database.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.masinew.database.hibernate.entity.ChampHi;

public class AnnotationApp {
	private static String configName = "annotation.hibernate.cfg.xml";
	public static void main(String[] a) {
//		testSaving();
		testGetting();
	}
	
	public static void testGetting() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure(configName)
			.build();
		
		SessionFactory ssFac = null;
		try {
			ssFac = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}
		catch(Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
		
		Session ss = ssFac.openSession();
		ss.beginTransaction();
		List<ChampHi> list = ss.createQuery("from ChampHi order by id desc").list();
		for (ChampHi c : list) {
			System.out.println(c.getName());
		}
		
		ss.getTransaction().commit();
		ss.close();
		
		if (ssFac != null) {
			ssFac.close();
		}
	}
	
	public static void testSaving() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure(configName)
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
