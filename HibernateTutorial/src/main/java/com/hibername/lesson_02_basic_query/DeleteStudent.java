package com.hibername.lesson_02_basic_query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudent {
	
	public static void main(String[] args) {
		
		// create session factory 
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// use the session object to save Java object
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		int studentID = 3;
		Student s = session.get(Student.class, studentID);

		session.delete(s);		
		session.getTransaction().commit();

		
		
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		session.createQuery("delete from Student where id=6").executeUpdate();
		
		session.getTransaction().commit();
		
	}
}
