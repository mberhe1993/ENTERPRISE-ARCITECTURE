package cs544.exercise16_1.bank.service;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cs544.exercise16_1.bank.dao.AccountDAO;
import cs544.exercise16_1.bank.dao.AccountDAOHibernate;
import cs544.exercise16_1.bank.dao.HibernateUtil;
import cs544.exercise16_1.bank.dao.IAccountDAO;
import cs544.exercise16_1.bank.domain.Account;
import cs544.exercise16_1.bank.domain.Customer;
import cs544.exercise16_1.bank.jms.IJMSSender;
import cs544.exercise16_1.bank.jms.JMSSender;
import cs544.exercise16_1.bank.logging.ILogger;
import cs544.exercise16_1.bank.logging.Logger;





public class AccountService implements IAccountService {

	private IAccountDAO accountDAO;

	private ICurrencyConverter currencyConverter;
	
	private IJMSSender jmsSender;
	
	private ILogger logger;
	
	private SessionFactory sf=HibernateUtil.getSessionFactory();
	private Session session;
	private Transaction transaction;
	
	public AccountService(IAccountDAO accountDAO,ICurrencyConverter currencyConverter,IJMSSender jmsSender,ILogger logger){
		this.accountDAO=accountDAO;
		this.currencyConverter=currencyConverter;
		this.jmsSender=jmsSender;
		this.logger=logger;
	}

	public Account createAccount(long accountNumber, String customerName) {
		session=sf.getCurrentSession();
		transaction=session.beginTransaction();
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		accountDAO.saveAccount(account);
		transaction.commit();
		logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		return account;
	}

	public void deposit(long accountNumber, double amount) {
		session=sf.getCurrentSession();
		transaction=session.beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);
		accountDAO.updateAccount(account);
		transaction.commit();
		logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amount > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	public Account getAccount(long accountNumber) {
		session=sf.getCurrentSession();
		transaction=session.beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		transaction.commit();
		return account;
	}

	public Collection<Account> getAllAccounts() {
		session=sf.getCurrentSession();
		transaction=session.beginTransaction();
		Collection<Account> accountList=accountDAO.getAccounts();
		transaction.commit();
		return accountList;
	}

	public void withdraw(long accountNumber, double amount) {
		session=sf.getCurrentSession();
		transaction=session.beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		account.withdraw(amount);
		accountDAO.updateAccount(account);
		transaction.commit();
		logger.log("withdraw with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	public void depositEuros(long accountNumber, double amount) {
		session=sf.getCurrentSession();
		transaction=session.beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.deposit(amountDollars);
		accountDAO.updateAccount(account);
		transaction.commit();
		logger.log("depositEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amountDollars > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	public void withdrawEuros(long accountNumber, double amount) {
		session=sf.getCurrentSession();
		transaction=session.beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
		accountDAO.updateAccount(account);
		transaction.commit();
		logger.log("withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		session=sf.getCurrentSession();
		transaction=session.beginTransaction();
		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
		transaction.commit();
		logger.log("transferFunds with parameters fromAccountNumber= "+fromAccountNumber+" , toAccountNumber= "+toAccountNumber+" , amount= "+amount+" , description= "+description);
		if (amount > 10000){
			jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount);
		}
	}
}
