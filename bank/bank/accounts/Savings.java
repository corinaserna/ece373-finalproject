package bank.accounts;

import bank.people.Person;

public class Savings {
	private int accountID;
	private double requiredMinAmount;
	private double amount;
	
	public Savings(){
		this.accountID = 0;
		//Require at least 25$ to keep the account open
		this.requiredMinAmount = 25.00;
		this.amount = 0;
	}
	
	/**
	 * @return the accountID
	 */
	public int getAccountID() {
		return accountID;
	}
	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	/**
	 * @return the requiredMinAmount
	 */
	public double getRequiredMinAmount() {
		return requiredMinAmount;
	}
	/**
	 * @param requiredMinAmount the requiredMinAmount to set
	 */
	public void setRequiredMinAmount(double requiredMinAmount) {
		this.requiredMinAmount = requiredMinAmount;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	//test
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @param add deposit to total
	 */
	public void addDeposit(double a) {
		this.amount = this.amount + a;
	}
	
	public void withdraw(double a) {
		// Should check for minimum amount 25$
		// May not withdraw if withdraw amount causes account amount of less than 25$
	}
}
