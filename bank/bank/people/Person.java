package bank.people;

public class Person {
	private String name;
	private String loginUsername;
	private String loginPassword;
	private double moneyOnHand;
	
	public Person(){
		this.name = null;
		this.loginPassword = null;
		this.loginUsername = null;
		this.moneyOnHand = 1000.00;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	
	public void addMoneyOnHand(double amount) {
		this.moneyOnHand += amount;
	}
	
	public double depositFromMoneyOnHand(double amount) {
		if(amount <= this.moneyOnHand) {
			this.moneyOnHand -= amount;
			return amount;
		}
		return 0;
	}
	
}
