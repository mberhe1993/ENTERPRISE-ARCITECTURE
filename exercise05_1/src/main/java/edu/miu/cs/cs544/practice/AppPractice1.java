package edu.miu.cs.cs544.practice;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AppPractice1 {
    private static SessionFactory sessionFactory;


    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;
        try {
            session= sessionFactory.openSession();
            tx= session.beginTransaction();
            Person p3=(Person) session.get(Person.class, 1L);
            tx.commit();
        }catch (HibernateException e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            if(session!= null){
                session.close();
            }
        }}}


