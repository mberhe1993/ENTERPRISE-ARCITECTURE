package cs544.exercise16_2;

import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Collection;

public class StudentDAO {

	private SessionFactory sf = HibernateUtil.getSessionFactory();

	public Student load(long studentid) {
		return (Student) sf.getCurrentSession().createQuery("Select distinct s From Student s where s.studentid = :studentid")
				.setParameter("studentid", studentid).setMaxResults(1).uniqueResult();
	}
}
