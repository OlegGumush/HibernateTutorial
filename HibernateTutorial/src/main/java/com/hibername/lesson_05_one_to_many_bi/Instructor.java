package com.hibername.lesson_05_one_to_many_bi;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="instructor")
public class Instructor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String emailName;

	// refers to "instructor" property in "Course" class
	@OneToMany(mappedBy="instructor",
			cascade = {CascadeType.MERGE , CascadeType.PERSIST , CascadeType.REFRESH})
	private List<Course> courses;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="instructor_detail_id")
	private InstructorDetail instructorDetail;
	
	
	
	public Instructor() {
		courses = new ArrayList<Course>();
	}

	public Instructor(String firstName, String lastName, String emailName) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailName = emailName;
	}

	public void add(Course course) {
		courses.add(course);
		course.setInstructor(this);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailName() {
		return emailName;
	}

	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailName="
				+ emailName + ", instructorDetail=" + instructorDetail + "]";
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public InstructorDetail getInstructorDetail() {
		return instructorDetail;
	}

	public void setInstructorDetail(InstructorDetail instructorDetail) {
		this.instructorDetail = instructorDetail;
	}
}









