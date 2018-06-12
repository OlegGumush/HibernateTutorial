package com.hibername.lesson_02_basic_query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudent {

	public static void main(String[] args) {
		
		// create session factory 
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// use the session object to save Java object
		Session session = factory.getCurrentSession();
		
		// create an object
		Student s = new Student("Oleg1", "Gumush1", "oleg.gumush@gmail.com");
		Student s1 = new Student("Oleg12", "Gumush12", "oleg.gumush@gmail.com");

		// start transaction
		session.beginTransaction();
		 
		System.out.println("Insert two students into the database");
		System.out.println(s);
		System.out.println(s1);
		// save the student object
		session.save(s);
		session.save(s1);

		// commit transaction
		session.getTransaction().commit();
		
		// close factory
		factory.close();
	}
}
