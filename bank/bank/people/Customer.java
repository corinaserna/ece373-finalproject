package bank.people;

import bank.accounts.Account;

public class Customer extends Person{
	private Account account;
	private double moneyInWallet;
	
	public Customer(){
		super();
	}
	
	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
	/**
	 * @return the moneyInWallet
	 */
	public double getMoneyInWallet() {
		return moneyInWallet;
	}
	/**
	 * @param moneyInWallet the moneyInWallet to set
	 */
	public void setMoneyInWallet(double moneyInWallet) {
		this.moneyInWallet = moneyInWallet;
	}
	
}
