package edu.miu.cs.cs544.examples;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Application {
	private static SessionFactory sessionFactory;

	static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Student.class,Course.class));
	}

	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			// Create new instance of student and set values in it
			 Student s1 = new Student("612328","Selam","Weldegebriel");
			 Student s2 = new Student("612388","Zebib","Hagos");
			 Student s3 = new Student("612233","Helen","Haile");
			session.persist(s1);
			session.persist(s2);
			session.persist(s3);
			 
			 Course c1=new Course("544","FPP");
	       
			c1.addStudent(s1);
			c1.addStudent(s2);
			c1.addStudent(s3);
			 
//			c1.getStudents().put(s1.getId(), s1);
//			c1.getStudents().put(s2.getId(), s2);
//			c1.getStudents().put(s3.getId(), s3);
	           
	            
			// save 
			session.persist(c1);
			System.out.println(c1);

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