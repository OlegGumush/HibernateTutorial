package com.hibername.lesson_03_one_to_one_uni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

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
			
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("FOund instructor " + tempInstructor);

			if(tempInstructor != null ) {
				System.out.println("Deleting: " + tempInstructor);
				
				// Note : well also delete assicuated "details" object
				// because of CascadeType.ALL
				session.delete(tempInstructor);
			}
			
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
