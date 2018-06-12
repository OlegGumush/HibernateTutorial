package com.hibername.lesson_02_basic_query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadObject {

	public static void main(String[] args) {
		
		// create session factory 
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// use the session object to save Java object
		Session session = factory.getCurrentSession();
		
		// create an object
		Student s = new Student("Stam", "Gumush1", "oleg123.gumush@gmail.com");
		
		// start transaction
		session.beginTransaction();
		
		System.out.println("Student without id:"+s);
		
		// save the student object
		session.save(s);

		System.out.println("Student with id:"+s);

		
		// commit transaction
		session.getTransaction().commit();
		
		// get a new session and start transaction
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		// retrieve student based on the id : primary key
		Student student = session.get(Student.class, s.getId());
		
		System.out.println("Retrived student " + student);
		// commit the transaction
		session.getTransaction().commit();
		
				
		// close factory
		factory.close();
	}
}
