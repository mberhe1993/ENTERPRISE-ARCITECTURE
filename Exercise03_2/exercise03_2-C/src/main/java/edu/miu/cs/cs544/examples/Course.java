package edu.miu.cs.cs544.examples;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String coursenumber;
	private String name;
	

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name="student_course")
	private Map<Long,Student> students=new HashMap<Long,Student>();

	public Course(String coursenumber, String name) {
		super();
		this.coursenumber = coursenumber;
		this.name = name;
	}
	
	public Course() {}

	public long getId() {
		return id;
	}


	public String getCoursenumber() {
		return coursenumber;
	}

	public void setCoursenumber(String coursenumber) {
		this.coursenumber = coursenumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public  Map<Long,Student> getStudents() {
		return students;
	}

	public void setStudents( Map<Long,Student> students) {
		this.students = students;
	}
	
	public void addStudent(Student student){
        if(student != null) {
            students.put(student.getId(), student);
        }

    }

	@Override
	public String toString() {
		return "Course [ coursenumber=" + coursenumber + ", name=" + name + ", students=" + students
				+ "]";
	}
	
	
	
	

}
