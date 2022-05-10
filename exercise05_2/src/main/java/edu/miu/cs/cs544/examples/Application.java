package edu.miu.cs.cs544.examples;

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
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Customer.class,Order.class,OrderLine.class,Product.class,Book.class,CD.class,DVD.class));
	}

	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			// Create new instance of Customer and set order on it
			
			Customer customer=new Customer();
			customer.setFirstname("Mhreteab");
			customer.setLastname("Adhanom");
			
			Order order1=new Order();
			order1.setOrderid("2116");
			order1.setDate(LocalDate.of(2022, 4, 7));
			
			Order order2=new Order();
			order2.setOrderid("1221");
			order2.setDate(LocalDate.of(2022, 2, 8));
			
			customer.addOrder(order1);
			customer.addOrder(order2);
			
			//create new instance of Orderline and set it in order
			OrderLine oL1=new OrderLine();
			oL1.setQuantity(5);
			OrderLine oL2=new OrderLine();
			oL2.setQuantity(10);
			OrderLine oL3=new OrderLine();
			oL3.setQuantity(20);
			
			order1.addOrderLine(oL1);
			order1.addOrderLine(oL2);
			order2.addOrderLine(oL3);
			
			//create new instance of product and set it in orderline
			DVD p1=new DVD();
			p1.setName("Home");
			p1.setDescription("care");
			p1.setGenre("Animation");
			

			
			CD p2=new CD();
			p2.setName("The Cruds");
			p2.setDescription("Animaton");
			p2.setArtist("Croge");
			
			Book p3=new Book();
			p3.setName("Introduction to Java");
			p3.setDescription("OOP");
			p3.setTitle("Basic java programming");
			
			
			oL1.setProduct(p1);
			oL2.setProduct(p3);
			oL3.setProduct(p2);
			
			
			//save customer
			session.persist(customer);
			
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
			// retrieve all customers
			List<Customer> customerList = session.createQuery("from Customer", Customer.class).list();
			for (Customer c : customerList) {
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