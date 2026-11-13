

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CheckingTest.
 *
 * @Katarina Liedbeck
 * @version1
 */
public class CheckingTest
{
    /**
     * Default constructor for test class CheckingTest
     */
    public CheckingTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
    
    /**
     * Testing the withdraw method.
     * precondtion : the equals method is created and works in test.
     * postcondition : The expected acc1 and expected acc2 are equivalent to acc1 and acc2, asserting True to the test method, which means
     * that the withdraw method works.
     * 
     */
    @Test
    public void testWithdraw()
    {
        //First we create 4 checking account objects.
        //expectedAcc1 is the expected account 1 after the withdraw method takes place.
        //expectedAcc2 is the expected account 2 after the withdraw method takes place.
        Money money1 = new Money (10, 0);
        Money money2 = new Money (5, 0);

        
        Checking acc1 = new Checking("Katie", "233422", money1, new Money (10,0) );
        Checking expectedAcc1 = new Checking("Katie", "233422", money2, new Money (10,0) );
            
        Checking acc2 = new Checking("Katie", "233422", money1, new Money (10,0));
        Checking expectedAcc2 = new Checking("Katie", "233422", new Money (-5,0) , new Money (10,0) );

        
        Money moneytoWithdraw = new Money (5,0);
        Money moneytoWithdraw2 = new Money (15,0);
        
        acc1.withdraw(moneytoWithdraw);
        acc2.withdraw(moneytoWithdraw2);
        
        assertTrue(acc1.equals(expectedAcc1)); 
        assertTrue(acc2.equals(expectedAcc2));
        
        //If the below comment is uncommented, the test should not work as we are withdrawing an insufficient amount .
        // acc1.withdraw(new Money(50000,0));
    }
}
