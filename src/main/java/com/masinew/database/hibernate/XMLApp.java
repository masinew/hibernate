package com.masinew.database.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.masinew.database.hibernate.entity.ChampHi;

public class XMLApp {
    public static void main( String[] args ) {
    	SessionFactory sessionFactory = null;
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    		.configure()
    		.build();
        try {
        	sessionFactory = new MetadataSources(registry)
        	.buildMetadata()
        	.buildSessionFactory();
        }
        catch(Exception e) {
        	StandardServiceRegistryBuilder.destroy(registry);
        }
        
        
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(new ChampHi("champ"));
        tx.commit();
        session.close();
        
        if (sessionFactory != null) {
        	sessionFactory.close();
        }
    }
}
