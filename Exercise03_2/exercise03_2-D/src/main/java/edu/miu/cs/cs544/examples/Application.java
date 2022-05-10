package edu.miu.cs.cs544.examples;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Application {
	private static SessionFactory sessionFactory;

	static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Customer.class,Reservation.class));
	}

	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			// Create new instance of reservation and set values in it
			List<Reservation> reserves=new ArrayList<>();
			Reservation r1=new Reservation();
			r1.setDate(LocalDate.of(2021, 7, 25));
			Reservation r2=new Reservation();
			r2.setDate(LocalDate.of(2021, 8, 15));
			reserves.add(r1);
			reserves.add(r2);
			
			
			Customer c1=new Customer();
			c1.setName("Selam");
			c1.setReservation(reserves);
			
	            
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
			// retrieve all reserves
			List<Reservation> reservationList = session.createQuery("from Reservation", Reservation.class).list();
			for (Reservation r : reservationList) {
				System.out.println(r);
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