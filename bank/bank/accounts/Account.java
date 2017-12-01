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
	private CreditCard creditCard;	
	
	public Account(Person belongsTo, String username, String password) {
		this.belongsTo = belongsTo;
		this.loginUsername = username;
		this.loginPassword = password;
		this.belongsTo.setLoginUsername(username);
		this.belongsTo.setLoginPassword(password);
		this.savingsAcct = new Savings();
		this.checkingAcct = new Checking();
		this.creditCard = new CreditCard();
		this.savingsAcct.addDeposit(this.belongsTo.depositFromMoneyOnHand(25.00));
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
		this.totalAmount = this.getSavingsAcct().getTotalAmount() + this.getCheckingAcct().getAmount();
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
			System.out.println("Not enough money.");
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
	
	/**
	 * @return the creditcard
	 */
	public CreditCard getCreditCard() {
		return creditCard;
	}

	/**
	 * @param creditcard the creditcard to set
	 */
	public void setCreditCard(CreditCard creditcard) {
		this.creditCard = creditcard;
	}
	
	public boolean depositSavings(double amount) {
		if(this.getBelongsTo().getMoneyOnHand() < amount) {
			return false;
		}
		this.setTotalAmount(this.getTotalAmount() + amount);
		this.getSavingsAcct().addDeposit(amount);
		this.getBelongsTo().depositFromMoneyOnHand(amount);
		return true;
	}

	public boolean withdrawSavings(double amount) {
		if(this.getSavingsAcct().getAvailableAmount() < amount) {
			return false;
		}
		this.getSavingsAcct().withdraw(amount);
		this.belongsTo.addMoneyOnHand(amount);
		return true;
	}	

	public boolean depositCheckings(double amount) {
		if(this.getBelongsTo().getMoneyOnHand() < amount) {
			return false;
		}
		this.setTotalAmount(this.getTotalAmount() + amount);
		this.getCheckingAcct().addDeposit(amount);
		this.getBelongsTo().depositFromMoneyOnHand(amount);
		return true;		
	}

	public boolean withdrawCheckings(double amount) {
		if(this.getCheckingAcct().getAmount() < amount) {
			return false;
		}
		this.getCheckingAcct().withdraw(amount);
		this.belongsTo.addMoneyOnHand(amount);
		return true;
	}	
	
	public boolean transferFromChecking(double amount) {
		if(this.getCheckingAcct().getAmount() >= amount) {
			this.getCheckingAcct().withdraw(amount);
			this.getSavingsAcct().addDeposit(amount);
			return true;
		}
		return false;
	}
	
	public boolean transferFromSavings(double amount) {
		if(this.getSavingsAcct().getAvailableAmount() >= amount) {
			this.getSavingsAcct().withdraw(amount);
			this.getCheckingAcct().addDeposit(amount);
			return true;
		}
		return false;
	}
	
	public boolean payCreditFromSavings(double amount) {
		if(this.getSavingsAcct().getAvailableAmount() >= amount) {
			this.getSavingsAcct().withdraw(amount);
			this.getCreditCard().payCard(amount);
			return true;
		}
		return false;
		
	}
	public boolean payCreditFromChecking(double amount) {
		if(this.getCheckingAcct().getAmount() >= amount) {
			this.getCheckingAcct().withdraw(amount);
			this.getCreditCard().payCard(amount);
			return true;
		}
		return false;
	}
	public boolean payCreditFromMoney(double amount) {
		if(this.getBelongsTo().getMoneyOnHand() >= amount) {
			this.getBelongsTo().depositFromMoneyOnHand(amount);
			this.getCreditCard().payCard(amount);
			return true;
		}
		return false;
		
	}
}
