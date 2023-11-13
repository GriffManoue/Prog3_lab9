package junitlab.bank;

import junitlab.bank.impl.FirstNationalBank;
import junitlab.bank.impl.GreatSavingsBank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankTestFixture {

    //FirstNationalBank bank;
    GreatSavingsBank bank;
    String accountNumber1;
    String accountNumber2;

    @Before
    public void setUp() throws AccountNotExistsException {
        //bank = new FirstNationalBank();
    	
    	bank = new GreatSavingsBank();

        accountNumber1 = bank.openAccount();
        accountNumber2 = bank.openAccount();

        bank.deposit(accountNumber1, 1500);
        bank.deposit(accountNumber2, 12000);
    }

    @Test
    public void testTransfer() throws NotEnoughFundsException, AccountNotExistsException {

        bank.transfer(accountNumber2, accountNumber1, 3456);

        assertEquals(4956, bank.getBalance(accountNumber1));
        assertEquals(8544, bank.getBalance(accountNumber2));

    }

    @Test (expected = NotEnoughFundsException.class)
    public void testTransferWithoutEnoughFunds() throws NotEnoughFundsException, AccountNotExistsException {

        bank.transfer(accountNumber1, accountNumber2, 3456);
    }

}