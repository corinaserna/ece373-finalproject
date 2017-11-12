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
		// TODO Auto-generated method stub
		
	}

	@Override
	public double withdrawSavings(double amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void depositCheckings(double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double withdrawCheckings(double amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void payCreditBill(double amount) {
		// TODO Auto-generated method stub
		
	}
	
	

}
