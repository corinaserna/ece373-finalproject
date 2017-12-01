package bank.accounts;

import bank.people.Person;

public class Business extends Account{

	public Business(Person belongsTo, String loginUsername, String loginPassword) {
		super(belongsTo, loginUsername, loginPassword);
		this.setMaxTransferAmount(1000.00);
			
	}
}
