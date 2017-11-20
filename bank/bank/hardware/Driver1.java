package bank.hardware;

import static org.junit.jupiter.api.Assertions.assertTrue;

import bank.accounts.Business;
import bank.accounts.Personal;
import bank.people.Administrator;
import bank.people.Customer;

public class Driver1 {
	public static void main(String[] args) {
		Bank bank = new Bank();
		
		Administrator ad1 = new Administrator();
		ad1.setName("Tom Jones");
		ad1.setTitle("Manager");
		ad1.setLoginUsername("tjones");
		ad1.setLoginPassword("password1");
		
		Customer p1 = new Customer();
		p1.setName("John Smith");
		String u1 = "jsmith";
		String pw1 = "hello123";
		
		Customer p2 = new Customer();
		p2.setName("Jane Doe");
		String u2 = "jdoe";
		String pw2 = "world123";
		
		Personal a1 = new Personal(p1, u1, pw1);
		
		Business a2 = new Business(p2, u2, pw2);
		
		bank.addCustomer(p1);
		bank.addCustomer(p2);
		bank.addAdmin(ad1);
		bank.addPersonalAccount(a1);
		bank.addBusinessAccount(a2);
		
		BankGUI newGUI = new BankGUI("BankGUI", bank);
	}
}
