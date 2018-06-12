package com.hibername.lesson_06_eager_vs_lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo2 {

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
			
			// hibernate query with HQL
			Query<Instructor> query = session.createQuery("select i from Instructor i JOIN FETCH i.courses where i.id=:theInstructorId"
					,Instructor.class);
			query.setParameter("theInstructorId", theId);
			
			Instructor instructor = query.getSingleResult();
			
			// Instructor LAZY on course class
			System.out.println("Instructor " + instructor);
			
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
