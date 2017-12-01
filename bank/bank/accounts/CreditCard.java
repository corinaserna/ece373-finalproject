package bank.accounts;

import java.util.Random;

public class CreditCard {
	private String cardNumber;
	private double creditLimit;
	private double creditBill;
	
	public CreditCard(){

		this.creditBill = 0.0;
		this.creditLimit = 500.00;
		StringBuilder builder = new StringBuilder();
		Random rand = new Random();
		
		for(int i = 0; i < 16; i++) {
			int digit = rand.nextInt(10);
			builder.append(digit);
		}
		
		this.cardNumber = builder.toString();
	}
	
	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
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
	 * @return the creditBill
	 */
	public double getAvailableCredit() {
		return this.creditLimit - creditBill;
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
