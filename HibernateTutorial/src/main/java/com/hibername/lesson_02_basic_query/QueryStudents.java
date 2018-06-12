 package com.hibername.lesson_02_basic_query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class QueryStudents {


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
		
		System.out.println(s);
		
		// save the student object
		session.save(s);

		// commit transaction
		session.getTransaction().commit();
				
		// get a new session and start transaction
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		System.out.println();
		System.out.println("\nfrom Student");
		List<Student> students = session.createQuery("from Student").getResultList();
		
		desplayStudents(students);
		
		System.out.println("\nfrom Student s where s.lastName='Gumush1'");
		students = session.createQuery("from Student s where s.lastName='Gumush1'").getResultList();

		desplayStudents(students);

		System.out.println("\nfrom Student s where s.lastName='Gumush1' OR s.firstName='Oleg'");
		students = session.createQuery("from Student s where s.lastName='Gumush1' OR s.firstName='Oleg'").getResultList() ;

		desplayStudents(students);
		
		System.out.println("\nfrom Student s where s.lastName='Gumush1' OR s.firstName='Oleg'");
		students = session.createQuery("from Student s where s.lastName='Gumush1' OR s.firstName='Oleg'").getResultList();
		
		desplayStudents(students);

		// close factory
		factory.close();
	}

	private static void desplayStudents(List<Student> students) {
		for (Student temp : students){
			System.out.println(temp);
		}
	}
}
