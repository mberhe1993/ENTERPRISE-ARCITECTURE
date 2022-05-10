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
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Book.class,Publisher.class));
	}

	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			// Create new instance of publisher and set values in it
			 Publisher p1 = new Publisher("John Smith");
			 Publisher p2 = new Publisher("Yakob Berhe");
			 session.persist(p1);
			 session.persist(p2);
	          
			
	            Book b1 = new Book("123", "Java","YoYo");
	            Book b2 = new Book("124", "Python","XOXO");
	            Book b3 = new Book("125", "C++","SOSO");
	            
	            b1.setPublisher(p1);
	            b3.setPublisher(p2);
	           
	            
			// save 
			session.persist(b1);
			session.persist(b2);
			session.persist(b3);
			System.out.println(b1);
			System.out.println(b2);
			System.out.println(b3);

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
			// retrieve all books
			List<Book> bookList = session.createQuery("from Book", Book.class).list();
			for (Book b : bookList) {
				System.out.println(b);
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