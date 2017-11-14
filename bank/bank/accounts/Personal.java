package bank.accounts;

import bank.people.Person;

public class Personal extends Account{
	private CreditCard creditcard;
	
	public Personal(Person belongsTo, String username, String password) {
		super(belongsTo, username, password);
		this.setMaxTransferAmount(500.00);
	}

	/**
	 * @return the creditcard
	 */
	public CreditCard getCreditcard() {
		return creditcard;
	}

	/**
	 * @param creditcard the creditcard to set
	 */
	public void setCreditcard(CreditCard creditcard) {
		this.creditcard = creditcard;
	}

	@Override
	public void depositSavings(double amount) {
		super.getSavingsAcct().addDeposit(amount);
	}

	@Override
	public double withdrawSavings(double amount) {
		if(super.getTotalAmount()-amount >= 25) {
			super.getTransferAmount(amount);
			return amount;
		}
		else {
			System.out.println("Account will have less than 25$ after withdrawal. ");
			return 0;
		}
	}

	@Override
	public void depositCheckings(double amount) {
		this.setTotalAmount(this.getTotalAmount() + amount);
	}

	@Override
	public double withdrawCheckings(double amount) {
		//TODO getting money from checking amount or amount from saving
		return 0;
	}

	@Override
	public void payCreditBill(double amount) {
		getCreditcard().payCard(amount);
	}
	
	

}
