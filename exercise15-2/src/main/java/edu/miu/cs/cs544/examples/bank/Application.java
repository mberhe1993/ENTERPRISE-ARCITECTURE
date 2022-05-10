package edu.miu.cs.cs544.examples.bank;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.miu.cs.cs544.examples.bank.domain.Account;
import edu.miu.cs.cs544.examples.bank.domain.AccountEntry;
import edu.miu.cs.cs544.examples.bank.domain.Customer;
import edu.miu.cs.cs544.examples.bank.service.AccountService;

public class Application {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");

		try {
			AccountService accountService = context.getBean(AccountService.class);
			// create 2 accounts;
			accountService.createAccount(1263862, "Frank Brown");
			accountService.createAccount(4253892, "John Doe");
			// use account 1;
			accountService.deposit(1263862, 240);
			accountService.deposit(1263862, 529);
			accountService.withdrawEuros(1263862, 230);
			// use account 2;
			accountService.deposit(4253892, 12450);
			accountService.depositEuros(4253892, 200);
			accountService.transferFunds(4253892, 1263862, 100, "payment of invoice 10232");

//			context.close();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			e.printStackTrace();
		}

	}

}
