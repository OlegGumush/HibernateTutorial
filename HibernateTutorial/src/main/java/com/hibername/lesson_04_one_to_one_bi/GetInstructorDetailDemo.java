package com.hibername.lesson_04_one_to_one_bi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		
		// create session factory 
		SessionFactory factory = new Configuration()
								.configure("hibernate.lesson.03.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();

		try {			
			// start a transaction
			session.beginTransaction();
			
			// get the instructor detaul object
			int theId = 2;
			InstructorDetail insDetail = session.get(InstructorDetail.class, theId);
			
			// print the associated instructor 
			System.out.println("Instructor detail : " + insDetail);
			System.out.println("Instructor : " + insDetail.getInstructor());

			// commit transaction
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		// close factory
		factory.close();
	}
}
