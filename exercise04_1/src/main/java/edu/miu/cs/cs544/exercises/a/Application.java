package edu.miu.cs.cs544.exercises.a;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Application {
	private static SessionFactory sessionFactory;

	static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Employee.class, Laptop.class));
	}

	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			 
			
			// Create new instance of employee and set values in it
			Employee employee=new Employee();
			employee.setFirstname("Selam");
			employee.setLastname("Weldegebriel");
			
			Laptop laptop1=new Laptop();
			laptop1.setBrand("Mac");
			laptop1.setType("MacBook Air");
			
			employee.addLaptop(laptop1);
			
			
			Laptop laptop2=new Laptop();
			laptop2.setBrand("Mac");
			laptop2.setType("MacBook Air");
			
			employee.addLaptop(laptop2);
			
			laptop1.setEmployee(employee);
			laptop2.setEmployee(employee);
			
			
			// save the employee
			session.persist(employee);
			System.out.println(employee);
		
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
			// retrieve all laptops
			List<Laptop> laptopList = session.createQuery("from Laptop", Laptop.class).list();
			for (Laptop l : laptopList) {
				System.out.println(l);
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