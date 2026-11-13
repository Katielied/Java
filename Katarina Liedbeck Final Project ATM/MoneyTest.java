import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// Test the Money class. 

public class MoneyTest 
{
    private Money _amount;

    /**
     * Default constructor for test class MoneyTest
     */
    public MoneyTest()
    {
        //System.out.println("JUnit Framework calls Constructor of test class before executing test methods");
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        _amount = new Money(0, 0);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
        _amount = null;
    }

    /**
     * Testing creating the money objects.
     * precondition: the methods getDollars() and getCents() are already created and defined.
     * postcondition: The test will be true if the money amounts dollars and cents are equivalent. 
     */
    @Test
    public void testCreate()
    {
         assertEquals("Error in testCreate", 0, _amount.getDollars());
         assertEquals("Error in testCreate", 0, _amount.getCents());
         
         Money amount2 = new Money (4, 50);
         
         assertEquals("Error in testCreate", 4, amount2.getDollars());
         assertEquals("Error in testCreate", 50, amount2.getCents());
         
         Money amount3 = new Money (-4, -50);
         
         assertEquals("Error in testCreate", -4, amount3.getDollars());
         assertEquals("Error in testCreate", -50, amount3.getCents());
         
    }
   
    /**
     * Testing the toString Method.
     * Precondition : the equals method works, and the creation of money method works.
     * postcondition : The method will assertTrue if the expected string is equivalent to when the toString() method is called upon the actual money object.
     */
    @Test
    public void testToString()
    {
        // First test: cents is two digits
        Money amount = new Money (7, 55);
        String actual = amount.toString();
        String expected = "$7.55";
        assertTrue("Error in testToString", actual.equals(expected));
        
        // Second test: cents is one digit
        Money amount2 = new Money (7, 5);
        String actual2 = amount2.toString();
        String expected2 = "$7.05";
        assertTrue("Error in testToString", actual2.equals(expected2));
    }
    
    /**
     * Test method used to test the equals method
     * precondition: money objects do not include negative values.
     * postcondition: Test works if test assertsTrue when money amounts are called upon each other using the equals() method.
     */
    @Test
    public void testEquality()
    {
        Money myCash = new Money (3, 75);
        Money yourCash = new Money (3, 75);
        
        assertTrue ("Error in testEquality", myCash.equals(yourCash));
        
        Money myAmount = new Money (50, 0);
        Money yourAmount = new Money (50, 0);
        
        assertTrue ("Error in testEquality", myAmount.equals(yourAmount));
    }
    
    /**
     * Testing if the equals  method can detect when the money amounts are not equivalent.
     * precondition: The equals method works when the money values are exactly the same .
     * postcondition : the assertFalse will make the test true, since the equals method should return false for inequality in money values .
     */
    @Test
    public void testInequality()
    {
        //Creating the money value which will be our stable variable.
        Money myCash = new Money (3, 75);
        
        //Creating 3 new money values, 
        //Different dollars same cents
        //Different dollars different cents
        //And finally same dollars and different cents.
        Money difftDollarsSameCents = new Money (4, 75);    
        Money difftDollarsDifftCents = new Money (4, 80);   
        Money sameDollarsDifftCents = new Money (3, 80);   
        
        //Asserting false to the equals methods () . 
        assertFalse ("Error in testInequality", myCash.equals(difftDollarsSameCents));
        assertFalse ("Error in testInequality", myCash.equals(difftDollarsDifftCents));
        assertFalse ("Error in testInequality", myCash.equals(sameDollarsDifftCents));
    }
    
    /**
     * Testing the simple addition of the add method.
     * precondition: the equals method is created and works in the test method.
     * postcondition : test method will assert True if the actual amount is equal to the expected amount after the add method is used .
     * 
     */
    @Test
    public void testSimpleAdd()
    {
       Money amount2 = new Money (2, 30);
       Money amount3 = new Money (3, 50);
       
       Money actualAmount = amount2.add (amount3);
       // actualAmount now has the sum of amount2 + amount 3
       
       // Expected result is $5.80
       Money expectedAmount = new Money (5, 80);
       
       assertTrue ("Error in testSimpleAdd", actualAmount.equals(expectedAmount));
       //assertEquals("Error in testSimpleAdd", 5, actualAmount.getDotllars());
       //assertEquals("Error in testSimpleAdd", 80, actualAmount.getCents());
    }
    
    /**
     * Testing the complex addition of the add() method
     * precondition: The simple add works, and the equals method works.
     * postcondition : The test method will assert True if the expected amount is equal to the actual amount after the add method is used.
     */
    @Test
    public void testComplexAdd()
    {
        // First test: sum of cents is 100.
        
        Money myCash = new Money (3, 50);
        Money yourCash = new Money (4, 50);            
        
        // Expected result is $8.00
        Money expectedAmount = new Money (8, 0);
       
        Money actualAmount = myCash.add(yourCash);
        
        //System.out.println (actualAmount.toString()); // just for tracing purposes
        
        assertTrue ("Error in testComplexAdd", actualAmount.equals(expectedAmount));
        
        
        // Second test: sum of cents is greater than 100...
        Money myMoney = new Money (3, 50);
        Money yourMoney = new Money (4, 70);
        
        Money expectedTotal = new Money (8, 20);
        
        Money actualTotal = myMoney.add(yourMoney);
        
        assertTrue ("Error in testComplexAdd", actualTotal.equals(expectedTotal));
        
        
    }

    /**
     * Testing the simple subtraction using the subtract() method .
     * precondition: the equals method is created and works in the test method of equals()
     * postcondition : the method will assertTrue if the actual amount is equal to the expected amount after the subtract() method is used .
     */
    @Test
    public void testSimpleSubtract()
    {
        //Creating the myCash and yourCash Money objects.
        Money myCash = new Money (4, 20);
        Money yourCash = new Money (1, 10);
        
        //Difference expected is a money object of 3 dollars and 10 cents.
        Money expectedAmount = new Money (3, 10);
        
        //Calling the subtract method, assigning it to the actual true amount.
        Money actualAmount = myCash.subtract(yourCash);
        
        assertTrue ("Error in testSimpleSubtract", actualAmount.equals(expectedAmount));
        
    }
    
    /**
     * Testing the complex subtraction using the subtract() method 
     * precondition: the subtract method with simple money values works, and the equals method is created and works.
     * postcondition : the method will assert True if the actual amount is equal to the expected amount after the subtract() method is used .
     */
    @Test
    public void testComplexSubtract()
    {
        //Creating two money objects to be subtracted from eachother.
        Money myCash = new Money (5, 50);
        Money yourCash = new Money (2, 80);
        
        //Creating an expected amount 
        Money expectedAmount = new Money (2, 70);
        
        //Creating the actual amount using the method.
        Money actualAmount = myCash.subtract(yourCash);
        
        //Using the equals method to compare the two to see if they are the same
        assertTrue ("Error in testComplexSubtract", actualAmount.equals(expectedAmount));
        
        
    }
    
    /**
     * Testing the compare() method when the amount is lower.
     * precondition: the compareTo method is created.
     * postcondition: the test method is asserted to True if the exted compare value is equal to the actual compare value after the method is used .
     */
    @Test
    public void testlowerAmount()
    {
        //Creating two money objects where myCash is lower.
        Money myCash = new Money (5, 50);
        Money yourCash = new Money (6, 10);
        
        //Creating an int expected variable.
        int expectedCompare = -1;
        
        //Creating an int actual variable using the compareTo method.
        int actualCompare = myCash.compareTo(yourCash);
        
        assertTrue ("Error in testlargerAmount", (expectedCompare == actualCompare));
    }
    
    /**
     * Testing the compare() method when the amount is larger.
     * precondition: the compareTo method is created, and the lower amount test works.
     * postcondition: the test method is asserted to True if the exted compare value is equal to the actual compare value after the method is used .
     */
    @Test
    public void testLargerAmount()
    {
        //Creating two money objects, where myCash is larger.
        Money myCash = new Money (5, 50);
        Money yourCash = new Money (4, 10);
        
        //Creating an int expected variable.
        int expectedCompare = 1;
        
        //Creating an int actual variable using the compareTo method.
        int actualCompare = myCash.compareTo(yourCash);
        
        assertTrue ("Error in testLowerAmount", (expectedCompare == actualCompare));
    }

    /**
     * Testing the compare() method when the amount is equals.
     * precondition: the compareTo method is created.
     * postcondition: the test method is asserted to True if the exted compare value is equal to the actual compare value after the method is used .
     */
    @Test
    public void testEqualsAmount()
    {
        //Creating two money objects to be equal to eachother.
        Money myCash = new Money (5, 50);
        Money yourCash = new Money (5, 50);
        
        //Creating an int expected variable.
        int expectedCompare = 0;
        
        //Creating an int actual variable using the compareTo method.
        int actualCompare = myCash.compareTo(yourCash);
        
        assertTrue ("Error in testEqualsAmount", (expectedCompare == actualCompare));
    }
    
}
