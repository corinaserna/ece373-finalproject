package bank.accounts;

import bank.people.Person;

public class Business extends Account{
	

	public Business(Person belongsTo, String loginUsername, String loginPassword) {
		super(belongsTo, loginUsername, loginPassword);
		this.setMaxTransferAmount(1000.00);
			
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
