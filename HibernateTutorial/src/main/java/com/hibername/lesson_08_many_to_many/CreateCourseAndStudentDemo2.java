package com.hibername.lesson_08_many_to_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentDemo2 {

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

			
			// get course
			Student student = session.get(Student.class, 2);

			System.out.println(student);
			System.out.println(student.getCourses());
			
			Course course1= new Course("course12112");
			Course course2= new Course("course12111");
			
			System.out.println();
			course1.addStudent(student);
			course2.addStudent(student);

			session.save(course1);
			session.save(course2);

			
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
