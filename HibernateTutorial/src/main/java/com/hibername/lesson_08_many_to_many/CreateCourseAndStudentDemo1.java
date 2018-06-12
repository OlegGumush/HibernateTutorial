package com.hibername.lesson_08_many_to_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentDemo1 {

	public static void main(String[] args) {
		
		// create session factory 
		SessionFactory factory = new Configuration()
								.configure("hibernate.lesson.09.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();

		try {
			
			// start a transaction
			session.beginTransaction();

			
			// create some courses
			Course course1= new Course("course12");

			// save courses
			session.save(course1);

			Student s1 = new Student("Oleg", "Gumush", "stam@gmail.com");
			Student s2 = new Student("Oleg1", "Gumush1", "stam1@gmail.com");

			course1.addStudent(s1);
			course1.addStudent(s2);
			
			session.save(s1);
			session.save(s2);

			
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
