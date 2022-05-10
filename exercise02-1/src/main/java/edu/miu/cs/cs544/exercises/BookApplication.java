package edu.miu.cs.cs544.exercises;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BookApplication {
	private static SessionFactory sessionFactory;

	static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Book.class));
	}

	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			// Create new instance of Employee and set values in it
			Book book1 = new Book("Java", "12345","Selam",50.5,new Date(12,7,2012));
			Book book2 = new Book("Python", "6789","Aron",80.5,new Date(10,8,2010));
			Book book3 = new Book("C++", "12346","Zebib",40.5,new Date(20,5,2008));
			
			
			
			// save the book
			session.persist(book1);
			session.persist(book2);
			session.persist(book3);
			System.out.println(book1);
//			session.persist(book1);
			System.out.println(book1);

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
		
		
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			// Retrieve a book from the database and change its title and price 
			Book book = (Book)session.get(Book.class, 1);
			book.setTitle("JavaFX");
			book.setPrice(100.20);
			Book book2 = (Book)session.load(Book.class, 2);
			session.delete(book2);
			session.flush();
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