package junitlab.bank;

import junitlab.bank.impl.FirstNationalBank;
import junitlab.bank.impl.GreatSavingsBank;

import org.junit.Test;

import static org.junit.Assert.*;

public class BankTest {

    @Test
    public void testOpenAccount() throws AccountNotExistsException {

       // FirstNationalBank bank = new FirstNationalBank();
    	
    	GreatSavingsBank bank = new GreatSavingsBank();

        String accountNumber = bank.openAccount();
        assertNotNull(accountNumber);

        long value = bank.getBalance(accountNumber);
        assertEquals(0, value);

    }

    @Test
    public void testUniqueAccount() {

       // FirstNationalBank bank = new FirstNationalBank();
    	
    	GreatSavingsBank bank = new GreatSavingsBank();


        String accountNumber1 = bank.openAccount();
        String accountNumber2 = bank.openAccount();

        assertNotEquals(accountNumber1, accountNumber2);
    }

    @Test (expected = AccountNotExistsException.class)
    public void testInvalidAccount() throws AccountNotExistsException {

       // FirstNationalBank bank = new FirstNationalBank();
    	
    	GreatSavingsBank bank = new GreatSavingsBank();

        bank.getBalance("123456789");

    }

    @Test
    public void testDeposit() throws AccountNotExistsException {

       // FirstNationalBank bank = new FirstNationalBank();
    	
    	GreatSavingsBank bank = new GreatSavingsBank();

        String accountNumber = bank.openAccount();
        bank.deposit(accountNumber, 2000);

        long value =  bank.getBalance(accountNumber);
        assertEquals(2000, value);
    }

}