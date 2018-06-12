package com.hibername.lesson_06_eager_vs_lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo1 {

	public static void main(String[] args) {
		
		// create session factory 
		SessionFactory factory = new Configuration()
								.configure("hibernate.lesson.06.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();

		try {			
			// start a transaction
			session.beginTransaction();
			
			// get instructor from db
			int theId = 1;
			Instructor instructor = session.get(Instructor.class, theId);

			// Instructor LAZY on course class
			System.out.println("Instructor " + instructor);
			
			// Lazy work only in same transaction LazyInitializationException
			// session.getTransaction().commit();
			// session.close();
			
			// here hibernate perform select courses
			for (Course string : instructor.getCourses()) {
				System.out.println("course: " + string);				
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
