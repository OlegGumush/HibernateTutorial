package com.hibername.lesson_05_one_to_many_bi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo2 {

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
			
			// create some courses
			Course course1= new Course("course1");
			Course course2= new Course("course2");
			Course course3= new Course("course3");

			// add courses to instructor
			instructor.add(course1);
			instructor.add(course2);
			instructor.add(course3);

			
			// save courses
			session.save(course1);
			session.save(course2);
			session.save(course3);


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
