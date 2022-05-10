package cs544.exercise17_2;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.MANDATORY)
public class StudentDAO {

	private SessionFactory sf ;

	@Transactional(propagation=Propagation.MANDATORY)
	public Student load(long studentid) {
		return (Student) sf.getCurrentSession().createQuery("Select distinct s From Student s where s.studentid = :studentid")
				.setParameter("studentid", studentid).setMaxResults(1).uniqueResult();
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sf=sessionFactory;
	}
}
