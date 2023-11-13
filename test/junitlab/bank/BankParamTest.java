package junitlab.bank;

import junitlab.bank.impl.FirstNationalBank;
import junitlab.bank.impl.GreatSavingsBank;
import org.junit.Test;
import org.junit.runners.Parameterized;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith( Parameterized.class)
public class BankParamTest {

    long amount;
    long rounded;

    public BankParamTest(long a, long r ){
        amount = a;
        rounded = r;
    }

    @Test
    public void testWithdrawRounding() throws AccountNotExistsException, NotEnoughFundsException {

        //GreatSavingsBank bank = new GreatSavingsBank();
        
        FirstNationalBank bank = new FirstNationalBank();

        String accountNumber = bank.openAccount();
        bank.deposit(accountNumber, 10000);

        bank.withdraw(accountNumber, amount);

        long value = bank.getBalance(accountNumber);

        assertEquals(rounded, 10000 -value);

    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1100, 1100},
                {1101, 1100},
                {1149, 1100},
                {1150, 1200},
                {1151, 1200},
                {1199, 1200},
                {1200, 1200}
        });
    }

}
