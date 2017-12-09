package bank.accounts;

public class Checking {
	private int accountID;
	private double requiredMinAmount;
	private double amount;

	
	public Checking(){
		this.accountID = 0;
		this.requiredMinAmount = 0;
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
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @param amount deposit to account
	 */
	public void addDeposit(double a) {
		this.amount = amount + a;
	}
	
	public boolean withdraw(double b) {
		if(this.getAmount() >= b) {
			this.amount -= b;
			return true;
		}
		return false;
	}
	/**
	 * @param Taking amount out from account
	 */
	
}
