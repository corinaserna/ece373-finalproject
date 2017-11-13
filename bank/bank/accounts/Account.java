package bank.accounts;
import bank.people.Person;

public abstract class Account {
	private Person belongsTo;
	private double totalAmount;
	private double maxTransferAmount;
	private String loginUsername;
	private String loginPassword;
	private Savings savingsAcct;
	private Checking checkingAcct;
	
	
	
	public Account(Person belongsTo, String username, String password) {
		this.belongsTo = belongsTo;
		this.loginUsername = username;
		this.loginPassword = password;
		this.savingsAcct = new Savings();
		this.checkingAcct = new Checking();
	}
	/**
	 * @return the belongsTo
	 */
	public Person getBelongsTo() {
		return belongsTo;
	}
	/**
	 * @param belongsTo the belongsTo to set
	 */
	public void setBelongsTo(Person belongsTo) {
		this.belongsTo = belongsTo;
	}
	/**
	 * @return the totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * @return the maxTransferAmount
	 */
	public double getMaxTransferAmount() {
		return maxTransferAmount;
	}
	public double getTransferAmount(double a) {
		if(this.totalAmount - a >= 25) {
			this.totalAmount = this.totalAmount - a;
			return a;
		}
		else {
			//not enough money;
			return 0;
		}
	}
	/**
	 * @param maxTransferAmount the maxTransferAmount to set
	 */
	public void setMaxTransferAmount(double maxTransferAmount) {
		this.maxTransferAmount = maxTransferAmount;
	}
	/**
	 * @return the loginUsername
	 */
	public String getLoginUsername() {
		return loginUsername;
	}
	/**
	 * @param loginUsername the loginUsername to set
	 */
	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}
	/**
	 * @return the loginPassword
	 */
	public String getLoginPassword() {
		return loginPassword;
	}
	/**
	 * @param loginPassword the loginPassword to set
	 */
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	/**
	 * @return the savingsAcct
	 */
	public Savings getSavingsAcct() {
		return savingsAcct;
	}
	/**
	 * @param savingsAcct the savingsAcct to set
	 */
	public void setSavingsAcct(Savings savingsAcct) {
		this.savingsAcct = savingsAcct;
	}
	/**
	 * @return the checkingAcct
	 */
	public Checking getCheckingAcct() {
		return checkingAcct;
	}
	/**
	 * @param checkingAcct the checkingAcct to set
	 */
	public void setCheckingAcct(Checking checkingAcct) {
		this.checkingAcct = checkingAcct;
	}
	public abstract void depositSavings(double amount);
	public abstract double withdrawSavings(double amount);
	public abstract void depositCheckings(double amount);
	public abstract double withdrawCheckings(double amount);
	public abstract void payCreditBill(double amount);
	public abstract void addDeposit(double amount);
}
