package edu.miu.cs.cs544.exercise;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Application {
	private static SessionFactory sessionFactory;

	static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Appointment.class, Patient.class,Payment.class,Doctor.class));
	}

	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			 
			
			// Create new instance of Appointment and set values in it
			Appointment appointment=new Appointment();
			appointment.setAppdate("15/05/08");
			Payment payment=new Payment();
			payment.setPaydate("12/06/08");
			payment.setAmount(100);
			appointment.setPayment(payment);
			
			//set patient table and address table
			Patient patient=new Patient();
			patient.setName("John Doe");
			patient.setStreet("100 Main Street");
			patient.setCity("Boston");
			patient.setZip("23114");
			
			patient.addAppointment(appointment);
			
			//set doctor table
			Doctor doctor =new Doctor();
			doctor.setDoctortype("Eye doctor");
			doctor.setFirstname("Frank");
			doctor.setLastname("Brown");
			appointment.setDoctor(doctor);
			
			
			
			// save the appointment
			session.persist(appointment);
			
		
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
			// retrieve all appointments
			List<Appointment> appointmentList = session.createQuery("from Appointment", Appointment.class).list();
			for (Appointment a : appointmentList) {
				System.out.println(a);
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