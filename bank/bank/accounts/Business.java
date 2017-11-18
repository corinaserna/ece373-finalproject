package bank.accounts;

import bank.people.Person;

public class Business extends Account{
	private CreditCard creditcard;

	public Business(Person belongsTo, String loginUsername, String loginPassword) {
		super(belongsTo, loginUsername, loginPassword);
		this.setMaxTransferAmount(1000.00);
		this.creditcard = new CreditCard();
			
	}
	
	public CreditCard getcreditCard() {
		return creditcard;
	}
	
	public void setCreditCard (CreditCard card) {
		this.creditcard = card;
	}

	@Override
	public void depositSavings(double amount) {
		this.setTotalAmount(this.getTotalAmount() + amount);
	}

	@Override
	public double withdrawSavings(double amount) {
		// TODO Auto-generated method stub
		if (super.getTransferAmount(amount) == 0) {
			System.out.println("Account will have less than 25$");
		}
		return 0;
	}

	@Override
	public void depositCheckings(double amount) {
		this.getCheckingAcct().addDeposit(amount);
		
	}

	@Override
	public double withdrawCheckings(double amount) {
		if(super.getTransferAmount(amount) == 0) {
			System.out.println("Account will have less than 25$ ");
		}
		
		return 0;
	}

	@Override
	public void payCreditBill(double amount) {
		//Does moeny get subtracted from total, or other accounts?
		//
		//
		this.getcreditCard().payCard(amount);
		
	}
}
