package bank.accounts;

import bank.people.Person;

public class Business extends Account{
	

	public Business(Person belongsTo, String loginUsername, String loginPassword) {
		super(belongsTo, loginUsername, loginPassword);
		this.setMaxTransferAmount(1000.00);
			
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
		
		
	}
}
