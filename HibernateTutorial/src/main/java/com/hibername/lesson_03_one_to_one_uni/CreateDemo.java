package com.hibername.lesson_03_one_to_one_uni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

	public static void main(String[] args) {
		
		// create session factory 
		SessionFactory factory = new Configuration()
								.configure("hibernate.lesson.03.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();

		try {
			
			// create the objects
			Instructor instructor = new Instructor("Oleg", "Gumush", "Oleg.gumush@gmail.com");
			InstructorDetail instructorDetail = new InstructorDetail("http://www.channel.com", "Love to code");
			
			Instructor instructor1 = new Instructor("Oleg1", "Gumush1", "Oleg.gumush@gmail.com1");
			InstructorDetail instructorDetail1 = new InstructorDetail("http://www.channel.com1", "Love to code1");
			
			// associate the objects
			instructor.setInstructorDetail(instructorDetail);			
			instructor1.setInstructorDetail(instructorDetail1);
			
			// start a transaction
			session.beginTransaction();
			
			// save the instructor
			//
			// Note: this will also sae the details object
			// because of Cascade.ALL
			//
			System.out.println("Saving Instructor and Instructor Details");
			session.save(instructor);
			session.save(instructor1);

			// commit transaction
			session.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		// close factory
		factory.close();
	}
}
