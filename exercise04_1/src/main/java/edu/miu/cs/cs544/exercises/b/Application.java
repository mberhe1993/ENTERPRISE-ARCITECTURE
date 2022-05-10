package edu.miu.cs.cs544.exercises.b;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Application {
	private static SessionFactory sessionFactory;

	static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Passenger.class, Flight.class));
	}

	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			 
			
			// Create new instance of Passenger and set values in it
			Passenger passenger=new Passenger();
			passenger.setName("Selam Weldegebriel");
			
			Flight flight1= new Flight();
			flight1.setFlightnumber("00011");
			flight1.setFrom("AbuDhabi");
			flight1.setTo("USA");
			flight1.setDate(LocalDate.of(2021, 01, 23));
			
			Flight flight2= new Flight();
			flight2.setFlightnumber("00022");
			flight2.setFrom("Texas");
			flight2.setTo("Iowa");
			flight2.setDate(LocalDate.of(2021, 06, 21));
			
			passenger.addFlight(flight1);
			passenger.addFlight(flight2);
			
			
			// save the passenger
			session.persist(passenger);
			System.out.println(passenger);
		
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
			// retrieve all flights
			List<Flight> flightList = session.createQuery("from Flight", Flight.class).list();
			for (Flight f : flightList) {
				System.out.println(f);
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