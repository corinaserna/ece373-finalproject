package bank.hardware;
import java.util.ArrayList;

import bank.accounts.Account;
import bank.accounts.Business;
import bank.accounts.Personal;
import bank.people.Customer;
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
	
	// Should there be remove methods for customer,
	// admin, personal and business accounts? (for in
	// the event that a customer might decide to leave 
	// the bank)
	
	
	public boolean addCustomer(Person p1) {
		if(!this.customers.contains(p1)) {
			this.customers.add(p1);
			return true;
		}
		return false;
	}
	
	public boolean addAdmin(Person p1) {
		if(!this.administrators.contains(p1)) {
			this.administrators.add(p1);
			return true;
		}
		return false;
	}
	
	public boolean addPersonalAccount(Account a1) {
		if(!this.personalAccounts.contains(a1)) {
			this.personalAccounts.add(a1);
			return true;
		}
		return false;
	}
	
	public boolean addBusinessAccount(Account a1) {
		if(!this.businessAccounts.contains(a1)) {
			this.businessAccounts.add(a1);
			return true;
		}
		return false;
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
		for(int i = 0; i < this.getBusinessAccounts().size(); i++){
			this.totalMoney += this.getBusinessAccounts().get(i).getTotalAmount();
			this.totalMoney += this.getBusinessAccounts().get(i).getCreditCard().getCreditBill();
		}
		for(int i = 0; i < this.getPersonalAccounts().size(); i++){
			this.totalMoney += this.getPersonalAccounts().get(i).getTotalAmount();
			this.totalMoney += this.getPersonalAccounts().get(i).getCreditCard().getCreditBill();
		}
		return totalMoney;
	}
	/**
	 * @param totalMoney the totalMoney to set
	 */
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Person validateUser(String username, String password) {
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getLoginUsername().equals(username)){
				if(customers.get(i).getLoginPassword().equals(password)) {
					return customers.get(i);
				}
			}
		}
		
		for(int i = 0; i < administrators.size(); i++) {
			if(administrators.get(i).getLoginUsername().equals(username)){
				if(administrators.get(i).getLoginPassword().equals(password)) {
					return administrators.get(i);
				}
			}
		}
		
		return null;		
	}
	
	public Account getAccount(Person p) {
		for(int i = 0; i < this.personalAccounts.size(); i++) {
			if(personalAccounts.get(i).getBelongsTo().equals(p)) {
				return personalAccounts.get(i);
			}
		}
		for(int i = 0; i < this.businessAccounts.size(); i++) {
			if(businessAccounts.get(i).getBelongsTo().equals(p)) {
				return businessAccounts.get(i);
			}
		}
		return null;
	}
	
	public String typeAccount(Person p) {
		if(this.customers.contains(p)) {
			return "customer";
		}
		else {
			return "admin";
		}
	}
	public boolean checkUsernameAvailable(String username, char bOrp){
		if(bOrp == 'p' ){
			for(int i = 0; i < this.getPersonalAccounts().size();i++){
				if(username.equals(this.getPersonalAccounts().get(i).getLoginUsername()) == true){
					return false;
				}
			}
		}
		else if (bOrp == 'b'){
			for(int i = 0; i < this.getBusinessAccounts().size();i++){
				if(username.equals(this.getBusinessAccounts().get(i).getLoginUsername()) == true){
					return false;
				}
			}
		}
		return true;
	}
	public boolean createPersonalAccount(String name, String username, String password) {
		Customer tempP = new Customer();
		if(checkUsernameAvailable(username, 'p') == true){
			tempP.setName(name);
			tempP.setLoginUsername(username);
			tempP.setLoginPassword(password);
			
			Personal tempAcct = new Personal(tempP, username, password);
			
			this.addCustomer(tempP);
			this.addPersonalAccount(tempAcct);	
			return true;
		}
		return false;
	}

	public boolean createBusinessAccount(String name, String username, String password) {
		Customer tempP = new Customer();
		if(checkUsernameAvailable(username, 'b') == true){

			tempP.setName(name);
			tempP.setLoginUsername(username);
			tempP.setLoginPassword(password);
			
			Business tempAcct = new Business(tempP, username, password);
			
			this.addCustomer(tempP);
			this.addBusinessAccount(tempAcct);
			return true;
		}
		return false;
	}

	public ArrayList<Account> getAllAccounts() {
		ArrayList<Account> allAccts = new ArrayList<Account>();
		allAccts.addAll(businessAccounts);
		allAccts.addAll(personalAccounts);
		return allAccts;
	}
	
}
