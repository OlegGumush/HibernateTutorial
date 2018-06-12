package com.hibername.lesson_07_one_to_many_uni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseAndReviewDemo3 {

	public static void main(String[] args) {
		
		// create session factory 
		SessionFactory factory = new Configuration()
								.configure("hibernate.lesson.08.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();

		try {
			
			// start a transaction
			session.beginTransaction();

			int theId = 10;
			Course course = session.get(Course.class,theId);
			
			System.out.println(course);
			System.out.println(course.getReviews());

			session.delete(course);
			
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
