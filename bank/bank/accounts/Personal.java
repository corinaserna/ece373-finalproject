package bank.accounts;

import bank.people.Person;

public class Personal extends Account{
	
	public Personal(Person belongsTo, String username, String password) {
		super(belongsTo, username, password);
		this.setMaxTransferAmount(500.00);
	}
}
