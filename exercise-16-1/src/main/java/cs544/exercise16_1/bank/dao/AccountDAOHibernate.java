package cs544.exercise16_1.bank.dao;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cs544.exercise16_1.bank.domain.Account;


@Repository
public class AccountDAOHibernate implements IAccountDAO {
	//Collection<Account> accountlist = new ArrayList<Account>();
	
	private SessionFactory sf=HibernateUtil.getSessionFactory();
	public void saveAccount(Account account) {
		sf.getCurrentSession().persist(account);
	}

	public void updateAccount(Account account) {
 
		sf.getCurrentSession().saveOrUpdate(account);
	}

	public Account loadAccount(long accountnumber) {
		Account account=(Account)sf.getCurrentSession().get(Account.class, accountnumber);
		if(account!=null) {
			return account;
		}
		return null;
	}
	public void update(Account account) {
		sf.getCurrentSession().saveOrUpdate(account);
	}
	public void delete(Account account) {
		sf.getCurrentSession().delete(account);
	}
	public Collection<Account> getAccounts() {
		Query q=sf.getCurrentSession().getNamedQuery("Account.All");
		return q.list();
	}

}

