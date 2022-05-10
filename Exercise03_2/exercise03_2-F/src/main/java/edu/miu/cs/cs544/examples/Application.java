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
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Department.class,Employee.class,Office.class));
	}

	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			// Create new instance of department and set values in it
			 Department d1 = new Department("ComputerScience");
	            session.save(d1);
			
	            Employee e1 = new Employee("123", "Selam");
	            Employee e2 = new Employee("456", "Yorda");
	            Employee e3 = new Employee("789", "Lidu");
	          
	            
	            Office o1=new Office();
	            o1.setRoomnumber("30");
	            o1.setBuilding("HH");
	          
	            
	            e1.setOffice(o1);
	            e2.setOffice(o1);
	            e3.setOffice(o1);
	            
	            d1.addemployee(e1);
	            d1.addemployee(e2);
	            d1.addemployee(e3);
	            
			// save 
			session.persist(d1);
			System.out.println(d1);

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
			// retrieve all employees
			List<Employee> employeeList = session.createQuery("from Employee", Employee.class).list();
			for (Employee e : employeeList) {
				System.out.println(e);
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