package junitlab.bank;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import junitlab.bank.AccountNotExistsException;
import junitlab.bank.NotEnoughFundsException;
import junitlab.bank.impl.GreatSavingsBank;

public class GreatSavingsBankTest {

    private GreatSavingsBank bank;

    @Before
    public void setUp() {
        bank = new GreatSavingsBank();
    }

    @Test
    public void testOpenAccount() {
        String accountNumber = bank.openAccount();
        assertNotNull(accountNumber);
        assertTrue(accountNumber.startsWith("47328000-"));
    }

    @Test
    public void testCloseAccount() throws AccountNotExistsException {
        String accountNumber = bank.openAccount();
        assertTrue(bank.closeAccount(accountNumber));
    }

    @Test(expected = AccountNotExistsException.class)
    public void testCloseNonExistingAccount() throws AccountNotExistsException {
        bank.closeAccount("nonexistent");
    }

    @Test
    public void testGetBalance() throws AccountNotExistsException {
        String accountNumber = bank.openAccount();
        assertEquals(0, bank.getBalance(accountNumber));
    }

    @Test(expected = AccountNotExistsException.class)
    public void testGetBalanceNonExistingAccount() throws AccountNotExistsException {
        bank.getBalance("nonexistent");
    }

    @Test
    public void testDeposit() throws AccountNotExistsException {
        String accountNumber = bank.openAccount();
        bank.deposit(accountNumber, 150);
        assertEquals(200, bank.getBalance(accountNumber)); // Assuming initial balance is 50
    }

    @Test(expected = AccountNotExistsException.class)
    public void testDepositNonExistingAccount() throws AccountNotExistsException {
        bank.deposit("nonexistent", 100);
    }

    @Test
    public void testWithdraw() throws AccountNotExistsException, NotEnoughFundsException {
        String accountNumber = bank.openAccount();
        bank.deposit(accountNumber, 200);
        bank.withdraw(accountNumber, 100);
        assertEquals(100, bank.getBalance(accountNumber));
    }

    @Test(expected = AccountNotExistsException.class)
    public void testWithdrawNonExistingAccount() throws AccountNotExistsException, NotEnoughFundsException {
        bank.withdraw("nonexistent", 100);
    }

    @Test(expected = NotEnoughFundsException.class)
    public void testWithdrawNotEnoughFunds() throws AccountNotExistsException, NotEnoughFundsException {
        String accountNumber = bank.openAccount();
        bank.withdraw(accountNumber, 100); // Assuming initial balance is 0
    }

    @Test
    public void testTransfer() throws AccountNotExistsException, NotEnoughFundsException {
        String sourceAccount = bank.openAccount();
        String targetAccount = bank.openAccount();
        bank.deposit(sourceAccount, 200);
        bank.transfer(sourceAccount, targetAccount, 100);
        assertEquals(100, bank.getBalance(sourceAccount));
        assertEquals(100, bank.getBalance(targetAccount));
    }

    @Test(expected = AccountNotExistsException.class)
    public void testTransferSourceAccountNotExists() throws AccountNotExistsException, NotEnoughFundsException {
        String targetAccount = bank.openAccount();
        bank.transfer("nonexistent", targetAccount, 100);
    }

    @Test(expected = AccountNotExistsException.class)
    public void testTransferTargetAccountNotExists() throws AccountNotExistsException, NotEnoughFundsException {
        String sourceAccount = bank.openAccount();
        bank.transfer(sourceAccount, "nonexistent", 100);
    }

    @Test(expected = NotEnoughFundsException.class)
    public void testTransferNotEnoughFunds() throws AccountNotExistsException, NotEnoughFundsException {
        String sourceAccount = bank.openAccount();
        String targetAccount = bank.openAccount();
        bank.transfer(sourceAccount, targetAccount, 100); // Assuming both accounts have zero balance
    }

    @Test (expected = IllegalArgumentException.class)
    public void testWithdrawRounding() throws AccountNotExistsException, NotEnoughFundsException {
        String accountNumber = bank.openAccount();
        bank.deposit(accountNumber, 10000);
        bank.withdraw(accountNumber, 0);
    }

@Test
    public void closeAccountTest() throws AccountNotExistsException {
        String accountNumber = bank.openAccount();
        bank.deposit(accountNumber, 1000);
    assertFalse(bank.closeAccount(accountNumber));
    }

@Test (expected = IllegalArgumentException.class)
    public void transferZero() throws AccountNotExistsException, NotEnoughFundsException {
        String sourceAccount = bank.openAccount();
        String targetAccount = bank.openAccount();
        bank.deposit(sourceAccount, 1000);
        bank.transfer(sourceAccount, targetAccount, 0);

    }

}

