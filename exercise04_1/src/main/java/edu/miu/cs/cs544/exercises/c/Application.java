package edu.miu.cs.cs544.exercises.c;


import java.util.Arrays;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Application {
	private static SessionFactory sessionFactory;

	static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(School.class, Student.class));
	}

	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			 
			
			// Create new instance of School and set values in it
			School school=new School();
			school.setName("Maharishi International University");
			
			Student student1= new Student();
			student1.setStudentid("612328");
			student1.setFirstname("Selamawit");
			student1.setLastname("Weldegebriel");
			
			System.out.println("this is student1"+student1);
			
			Student student2= new Student();
			student2.setStudentid("612323");
			student2.setFirstname("Zebib");
			student2.setLastname("Hagos");
			
			school.addStudent(student1);
			school.addStudent(student2);
			
			
			// save the school
			session.persist(school);
			System.out.println(school);
		
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			// retrieve all students
			List<Student> studentList = session.createQuery("from Student", Student.class).list();
			for (Student s : studentList) {
				System.out.println(s);
			}
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		
		// Close the SessionFactory (not mandatory)
		sessionFactory.close();
	}
}