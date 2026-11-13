

import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class BankTest.
 *
 * @Katarina Liedbeck
 * @version1 04/17/2023
 */
public class Bank2Test
{
    private Account _account;
    private Bank _accounts;
    private Money _balance;
    
    //Since my BankTest class was not dependant on arrays, I am using the 
    //same unit test class however changing the Bank object to BankInterface, and also Bank to Bank2
    
    /**
     * Default constructor for test class BankTest
     */
    public Bank2Test()
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
     * Testing the equals method
     * precondition : format of the accounts are correct and valid.
     * postcondition: test will assertTrue if the two accounts are exaclty equivalent.
     */
    @Test
    public void testEquals()
    {
        Account test1 = new Account("Kat Liedbeck", "456", new Money(500,0));
        Account test2 = new Account("Kat Liedbeck", "456", new Money(500, 0));
        
        assertTrue(test1.equals(test2));
    }
    
    /**
     * Test method for testSetName
     * Creating a new bank2, using the setName() method to set a new name, using getName() method to return name of the bank, using equals()
     * method to check if expected and actual result are the same.
     * precondition: the equals() method is created and works in test. 
     * postcondition :AssertTrue if the banks name is equivalent to the string,
     */
    @Test
    public void testSetName()
    {
        //Creating a test for setting the name of the bank.
        //First we add an account into the array of accounts in the bank.
        Account account1 = new Account("Katie", "455", new Money(0,0));
        
        //Next we create a new bank object
        BankInterface bank1 = new Bank2();
        //Here we use the method set name for the bank.
        bank1.setName("Katie's Bank");
        
        //Creating the assertTrue statement, where getName method is used, equals to the string of the name of the bank.
        assertTrue((bank1.getName()).equals("Katie's Bank"));
        
    }
    
    /**
     * Testing the toString() method.
     * Creating a new bank2, setting the name using the setName() method, and adding an account using the addAcc() method.
     * precondition: the equals() method is created and works in test. 
     * postcondition: AssertTrue if the expected string and calling the toString() method on the bank1 are the same.
     */
    @Test
    public void testToString()
    {
        //Testing the to String method 
        //First we create a new bank
        //We add a name to the bank
        //We also add an account to the array of accounts.
        BankInterface bank1 = new Bank2();
        bank1.setName("Katie's Bank");
        bank1.addAcc(new Account("Katarina Liedbeck", "456", new Money(500,0)));
        
        //Using the to String method.
        System.out.println(bank1.toString());
        
        //Setting the method of bank1.toString equal to what the method would print.
        assertTrue(bank1.toString().equals("The name of the bank is :Katie's Bank\nThe number of accounts are :1\nThe accounts are :\nAccount name: Katarina Liedbeck\nAccount id: 456\nAccount Balance: $500.00\n\n"));
        
        
        
    }
    
    /**
     * Testing the testAddAcc() method.
     * Using the getNumOfAcc() method.
     * Creating a new Bank2, and adding 2 accounts to the bank.
     * precondition: the getNumOfAcc() method is created and works.
     * postcondition :AssertTrue if the number of accounts in the bank is equal to 2.
     */
    @Test
    public void testAddAcc()
    {
        //Creating a new bank object.
        BankInterface bank1 = new Bank2();
        
        //Adding 2 accounts to the array of accounts.
        bank1.addAcc(new Account("Katarina Liedbeck", "456", new Money(500,0)));
        bank1.addAcc(new Account("Nassia Podara", "567", new Money(200, 0)));
        
        //Setting the get method of the number of accounts in the bank to 2.
        assertTrue(bank1.getNumOfAcc() == 2);
        
        
    }
    
    /**
     * Testing the Search() method 
     * Creating a new Bank2, and adding 2 accounts into the bank, then searching first for an account existing, and also for an 
     * account which does not exist.
     * precondition: The addAcc() and the equals() method are created and work in tests.
     * postcondition: the test will assert True if the expected accounts / null are equal to the method called on the bank .
     */
    @Test
    public void testSearch()
    {
        //Creating a new bank object.
        BankInterface bank = new Bank2();
        
        bank.setName("Ka†ie's Bank");
        
        //Adding 2 new accounts into the array of accounts of the banks.
        Account test2 = new Account("Nassia Podara", "567", new Money(200, 0));
        Account test1 = new Account("Katarina Liedbeck", "456", new Money(500,0));
        
        
        bank.addAcc(test1);
        bank.addAcc(test2);
        
        
        //Using the search method to find the account id of 456, setting it to the actual account.
        Account actual = bank.search("456");
        
        //Setting expected account to the account which is expected from the search method.
        //Account expected = new Account("Katarina Liedbeck", "456", new Money(500,0));
        
        //Comparing the actual equal to the expected.
        assertTrue(actual.equals(test1));
        
        //Searching for a non existant accountr id.
        Account actual2 = bank.search("444");
        
        //The non existant account should equal to null, as there is none.
        assertTrue(actual2 == null);
        
    }
    
    /**
     * Testing the withdraw() method
     * Creating a new bank and adding an account to withdraw from.
     * precondition: the addAcc() and equals() methods are created and work in tests.
     * postcondition: Asserting true if the expected account is equivalent (including balance) to the account after the method is used. 
     */
    @Test public void testWithdraw()
    {
        //Creating a new bank object.
        BankInterface bank1 = new Bank2();
        
        //Adding an account to the array of accounts.
        bank1.addAcc(new Account("Katarina Liedbeck", "456", new Money(1000,0)));
        
        //withdrawing from the account, using the withdraw method.
        bank1.withdraw("456", new Money(500,0));
        
        //Setting the account expected to the same account, after the withdraw of money.
        Account expected = new Account("Katarina Liedbeck", "456", new Money(500,0));
        
        //Using the search method to find the new bank1 and seeing if it is equal to the expected.
        assertTrue(bank1.search("456").equals(expected));
        
    }
    
    /**
     * Testing the deposit() method
     * Creating a new bank and adding an account to deposit from.
     * precondition: the addAcc() and equals() methods are created and work in tests.
     * postcondition : Asserting true if the expected account is equivalent (including balance) to the account after the method is used.
     */
    @Test public void testDeposit()
    {
        //Creating a new bank object 
        BankInterface bank1 = new Bank2();
        
        //Adding a new account to the array in bank object.
        bank1.addAcc(new Account("Katarina Liedbeck", "456", new Money(1000,0)));
    
        //Depositting to the account using the deposit method.
        bank1.deposit("456", new Money(500,0));
        
        //Setting the account expected to the same account, after the deposit of the money.
        Account expected = new Account("Katarina Liedbeck", "456", new Money(1500,0));
        
        //Using the search method to find the new bank1 and seeing if it is equal to the expected.
        assertTrue(bank1.search("456").equals(expected));
        
        
    }
}
