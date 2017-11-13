package bank.accounts;

public class CreditCard {
	private int cardNumber;
	private double creditLimit;
	private double creditBill;
	
	public CreditCard(){
		this.cardNumber = 0;
		this.creditLimit = 0;
		this.creditBill = 0;
	}
	
	/**
	 * @return the cardNumber
	 */
	public int getCardNumber() {
		return cardNumber;
	}
	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	/**
	 * @return the creditLimit
	 */
	public double getCreditLimit() {
		return creditLimit;
	}
	/**
	 * @param creditLimit the creditLimit to set
	 */
	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}
	/**
	 * @return the creditBill
	 */
	public double getCreditBill() {
		return creditBill;
	}
	/**
	 * @param creditBill the creditBill to set
	 */
	public void setCreditBill(double creditBill) {
		this.creditBill = creditBill;
	}
	/**
	 * @param creditBill the creditBill to set
	 */
	public void payCard(double amount) {
		this.creditBill = this.creditBill - amount;
	}
	
	
	
		
	
}
