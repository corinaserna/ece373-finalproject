import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bank.accounts.*;
import bank.hardware.*;
import bank.people.*;

class BankTest1 {

	@Test
	void test() {
		//need to fix test JUnit test
		Bank bank = new Bank();
		
		Administrator ad1 = new Administrator();
		ad1.setName("Tom Jones");
		ad1.setTitle("Manager");
		ad1.setLoginUsername("tjones");
		ad1.setLoginPassword("password1");
		
		assertEquals("Tom Jones", ad1.getName());
		assertEquals("Manager", ad1.getTitle());
		assertEquals("tjones", ad1.getLoginUsername());
		assertEquals("password1", ad1.getLoginPassword());		
		
		Customer p1 = new Customer();
		p1.setName("John Smith");
		String u1 = "jsmith";
		String pw1 = "hello123";
		
		assertEquals("John Smith", p1.getName());	
		
		Customer p2 = new Customer();
		p2.setName("Jane Doe");
		String u2 = "jdoe";
		String pw2 = "world123";
		
		assertEquals("Jane Doe", p2.getName());	
		
		Personal a1 = new Personal(p1, u1, pw1);
		
		assertEquals("John Smith", a1.getBelongsTo().getName());
		assertEquals("jsmith", a1.getLoginUsername());
		assertEquals("hello123", a1.getLoginPassword());
		assertEquals(500.00, a1.getMaxTransferAmount());
		assertNotNull(a1.getCreditCard().getCardNumber());
		//assertEquals(25.00, a1.getSavingsAcct().getAmount());
		
		Business a2 = new Business(p2, u2, pw2);
		
		assertEquals("Jane Doe", a2.getBelongsTo().getName());
		assertEquals("jdoe", a2.getLoginUsername());
		assertEquals("world123", a2.getLoginPassword());
		assertEquals(1000.00, a2.getMaxTransferAmount());
		//assertEquals(25.00, a2.getSavingsAcct().getAmount());
		
		
		assertTrue(bank.addCustomer(p1));
		assertTrue(bank.addCustomer(p2));
		assertTrue(bank.addAdmin(ad1));
		assertTrue(bank.addPersonalAccount(a1));
		assertTrue(bank.addBusinessAccount(a2));
		
		BankGUI newGUI = new BankGUI("BankGUI", bank);
		
	}

}
