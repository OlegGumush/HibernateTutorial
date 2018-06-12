package com.hibername.lesson_02_basic_query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateObjects {

	public static void main(String[] args) {
		
		// create session factory 
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// use the session object to save Java object
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		int studentID = 1;
		Student s = session.get(Student.class, studentID);
		
		s.setFirstName("Update");
		
		//here we update database
		session.getTransaction().commit();
		
		session = factory.getCurrentSession();
		session.beginTransaction();

		session.createQuery("update Student set email='blabla@gmail.com'").executeUpdate();
		session.getTransaction().commit();

		
		
	}
}
