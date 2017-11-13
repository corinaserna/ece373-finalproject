import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bank.accounts.*;
import bank.hardware.*;
import bank.people.*;

class AccountCreditCard {

	@Test
	void test() {
		Bank bank = new Bank();
		Person person = new Person();
		person.setName("John Smith");
		String user = "jsmith";
		String password = "hello123";
		
		CreditCard c1 = new CreditCard();
		
		
		assertNotEquals(c1.getCardNumber(), null);
		assertEquals(c1.getCreditBill(), 0.0);
		assertEquals(c1.getCreditLimit(), 500.0);
		
		
		Account a1 = new Personal(person, user, password);
				
		assertEquals("John Smith", a1.getBelongsTo().getName());
		assertEquals("jsmith", a1.getLoginUsername());
		assertEquals("hello123", a1.getLoginPassword());
		
		
	}

}
