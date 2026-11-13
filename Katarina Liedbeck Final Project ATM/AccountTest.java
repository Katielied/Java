
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class AccountTest.
 *
 * @Katarina Liedbeck
 * @version1 04/17/2023
 */
public class AccountTest
{
    private Account _account;
    private Money _balance;
    
    /**
     * Default constructor for test class AccountTest
     */
    public AccountTest()
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
        //Creating an empty account before each test method
        Money money = new Money(0,0);
        _account = new Account ("", "", money);
    }
 
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        //Setting the account to null after each test method.
        _account = null;
    }
    
    /**
     * Testing the Equals() method.
     * Creating 2 Account type objects which are identical, and 1 which is different.
     * precondition: the equals method has been created already.
     * postcondition: AssertTrue if boolean actualResult (using the equals method on acc1 with acc2) is true,
     * AssertFalse if boolean actualResult2 is false.
     */
    @Test
    public void testEquals()
    {
        //Creating the same account objects for both accounts to test the equals method.
        Account acc1 = new Account ("Katarina Liedbeck", "488958", new Money(150,50));
        Account acc2 = new Account ("Katarina Liedbeck", "488958", new Money(150,50));
        
        //Declared using data type boolean, returning either true or false.
        boolean actualResult = acc1.equals(acc2);
        
        //AssetTrue if the actualResult is true.
        assertTrue(actualResult);
        
        //Creating a new account which is different.
        Account acc3 = new Account("Katie Liedbeck", "12345678", new Money(200,00));
        
        //Declaring actualResult2 as a boolean, returning wither true or false.
        boolean actualResult2 = acc2.equals(acc3);
        
        //AssertFalse if actualResult2 = false.
        assertFalse(actualResult2);
    }
    
    
    /**
     * Testing the toString() method.
     * precondition: The to String method is already created, the equals method is already created and works in test.
     * postcondition : asserTrue if the expected and actual results are equivalent , meaning that the toString method works.
    */
    @Test
    public void testToString()
    {
        //Setting up an object account to use the toString method on.
        Account acc1 = new Account ("Katarina Liedbeck", "483311", new Money(10000000,0));
        
        //Creating an expectedResult with data type String and an Actual result using the toString method
        String expectedResult = "Account name: Katarina Liedbeck\nAccount id: 483311\nAccount Balance: $10000000.00\n"; 
        String actualResult = acc1.toString();
        
        //Comparing the expected and actual result using the equals method.
        assertTrue ("Error in testToString", actualResult.equals(expectedResult));
    }
    
    /**
     * Testing the deposit method.
     * precondition: The equals method is created and works in test.
     * postcondition: The method will assertTrue if acc1 and _account are equivalent, which means that the dposit method works.
     */
    @Test
    public void testDeposit()
    {
        //Setting up an account object with the same name and id as the initial set up account, changing the balance 
        //of acc 1 to the expected balance of depositing 500,00 into _account.
        Account acc1 = new Account ("", "",new Money(500,50));
        
        //Using the deposit method to deposit money into _account.
        _account.deposit(new Money(500,50));
        
        //Comparing the two accounts, using the equals method to check if they are the same. 
        assertTrue ("Error in testDeposit", acc1.equals(_account));
    }
    
    /**
     * Testing the withdraw method.
     * precondition: The equals method is created and works in test.
     * postcondition: The method will assertTrue if expectedAcc and acc are equivalent, which means that the Withdraw method works.
     */
    @Test
    public void testWithdraw()
    {
        //Creating the expected account after withdrawing.
        Account expectedAcc = new Account ("Katie Liedbeck", "438766",new Money(500,00));
        
        //Creating the account which we are calling the method on.
        Account acc = new Account ("Katie Liedbeck", "438766", new Money(1000,00));
       
        //Withdrawing money using the withdraw method, passing through the parameters the amount of money being withdrawn.
        acc.withdraw(new Money(500,00)); 
       
        //Comparing the expected result (acc1) with the result in acc2 after using the method.
        assertTrue ("Error in testWithdraw", acc.equals(expectedAcc));
    }
    
    /**
     * Testing the transfer() method.
     * Creating 4 accounts, two are expected accounts and 2 are the accounts which the method is called upon.
     * precondition: the deposit, withdraw, and equals methods all work .
     * postcondition: AssertTrue if acc1 is equal to the expectedAcc1 AND if acc2 is equal to expectedAcc2.
     */
    @Test
    public void testTransfer()
    {
        //Creating acc1 as an object of type Account
        Account acc1 = new Account ("Katie Liedbeck", "489898", new Money(10000,00));
        
        //Creating the expectedAcc1, which is acc1 after the transfer.
        Account expectedAcc1 = new Account ("Katie Liedbeck", "489898", new Money(9500,00));
        
        //Creating acc2 as an object of type Account.
        Account acc2 = new Account ("Katarina Liedbeck", "400040", new Money(10000,00));
        
        //Creating the expectedAcc2, which is acc2 after the transfer.
        Account expectedAcc2 = new Account ("Katarina Liedbeck", "400040", new Money(10500,00));
        
        //Using the transfer method, passing through the parameters the acc which the money is being transferred to.
        //And the amount being transferred.
        acc1.transfer(acc2, new Money(500,00));
        
        //Using the equals method, where both acc1 and expectedAcc1 and acc2 and expectedAcc2 have to be true.
        assertTrue("Error in testTransfer", (acc1.equals(expectedAcc1)) && acc2.equals(expectedAcc2));
    }
}
