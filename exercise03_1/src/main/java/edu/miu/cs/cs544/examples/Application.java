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
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Car.class, Owner.class));
	}

	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			 Owner owner1 = new Owner("selam", "fairfield");
			 Owner owner2 = new Owner("zebib", "texas");
			 
			
			// Create new instance of car and set values in it
			Car car1 = new Car("Toyota", "2005",5000.50,owner2);
			Car car2 = new Car("Pirus", "2006",4000.50,owner1);
			
			
			// save the car
			session.persist(car1);
			session.persist(car2);
			System.out.println(car1);
			System.out.println(car2);
			

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
			// retrieve all cars
			List<Car> carList = session.createQuery("from Car", Car.class).list();
			for (Car c : carList) {
				System.out.println(c);
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