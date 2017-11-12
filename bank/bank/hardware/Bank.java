package bank.hardware;
import java.util.ArrayList;

import bank.accounts.Account;
import bank.people.Person;

public class Bank {
	private ArrayList<Person> customers;
	private ArrayList<Person> administrators;
	private ArrayList<Account> businessAccounts;
	private ArrayList<Account> personalAccounts;
	private double totalMoney;
	
	public Bank(){
		this.customers = new ArrayList<Person>();
		this.administrators = new ArrayList<Person>();
		this.businessAccounts = new ArrayList<Account>();
		this.personalAccounts = new ArrayList<Account>();
		this.totalMoney = 0.0;		
	}
	
	
	/**
	 * @return the customers
	 */
	public ArrayList<Person> getCustomers() {
		return customers;
	}
	/**
	 * @param customers the customers to set
	 */
	public void setCustomers(ArrayList<Person> customers) {
		this.customers = customers;
	}
	/**
	 * @return the administrators
	 */
	public ArrayList<Person> getAdministrators() {
		return administrators;
	}
	/**
	 * @param administrators the administrators to set
	 */
	public void setAdministrators(ArrayList<Person> administrators) {
		this.administrators = administrators;
	}
	/**
	 * @return the businessAccounts
	 */
	public ArrayList<Account> getBusinessAccounts() {
		return businessAccounts;
	}
	/**
	 * @param businessAccounts the businessAccounts to set
	 */
	public void setBusinessAccounts(ArrayList<Account> businessAccounts) {
		this.businessAccounts = businessAccounts;
	}
	/**
	 * @return the personalAccounts
	 */
	public ArrayList<Account> getPersonalAccounts() {
		return personalAccounts;
	}
	/**
	 * @param personalAccounts the personalAccounts to set
	 */
	public void setPersonalAccounts(ArrayList<Account> personalAccounts) {
		this.personalAccounts = personalAccounts;
	}
	/**
	 * @return the totalMoney
	 */
	public double getTotalMoney() {
		return totalMoney;
	}
	/**
	 * @param totalMoney the totalMoney to set
	 */
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	
}
