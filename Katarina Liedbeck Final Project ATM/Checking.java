
/**
 * @Katarina Liedbeck 
 * @version1 
 */
public class Checking extends Account
{
    private Money overdraftMax;

    /**
     * Constructor for objects of class Checking
     */
    public Checking(String theName, String theid, Money theBalance, Money om)
    {
        //Creating the constructor, using the same parameters as the super class of Account.
        //Creating the new attribute of overdftatMaximum
        
        super(theName, theid, theBalance);
        this.overdraftMax = om;
    }

    /**
     * Overriding the withdraw method used in account class.
     * Precondition: An account exists and it is of type checking, the method getBalance, add(), class
     * insufficient funds exception and compareTo already exist.
     * Postcondition: The method will add the overdraft balance to the current balance of the account, and withdraw the moneyToSubtract
     * taking into account the overdraft maximum. If the amount of money to subtract is greater than or equal to the total overdraftBalance
     * then it will withdraw from the account, if not it will throw the insufficient funds exception.
     * 
     * @param Money moneyToSubtract, money user wishes to withdraw from account.  
     */
    @Override
    public void withdraw(Money moneyToSubtract)
    {
        //Using the getbalance method from the super class.
        Money currentBalance = super.getbalance();
        
        //Adding the overdraftmaximum to the current balance. 
        Money overdraftBalance = currentBalance.add(overdraftMax);
        
        //Relating the if condition to the compare to method in the money class. 
        if (overdraftBalance.compareTo(moneyToSubtract) == 1 || overdraftBalance.compareTo(moneyToSubtract) == 0)
        {
            //If the above condition is true, we will be withdrawing the money, which comes from the super class. 
            super.withdraw(moneyToSubtract);
        }
        else
        {
            //Calling the insufficient funds exception, adding the string as a parameter.
            throw new InsufficientFundsException("You are unable to withdraw that much money, please choose a smaller amount.");
        }
    }
    
    /**
     * The method is used to get the overdraft maximum of the account.
     * Precondition: The account exists and is of type checking.
     * Postcondition: The method will return the overdraft maximum of the account. 
     * 
     * @return this.overdraftMax, returns the overdraft maximum of the type Checking Account.
     */
    public Money getOM()
    {
        return this.overdraftMax;
    }
    
    /**
     * Method used to get the total amount of money
     * precondition: There is a checking account with a balance and overdraft max,
     * ¨postcondition: The method will return the total amount of money, with both the balance and the overdraft maximum.
     */
    public Money getTotalMoney()
    {
        return super.getbalance().add(this.overdraftMax);
    }
    
    /**
     * Method used to return the account type, checking.
     * precondition: There is an account created of type checking.
     * postcondition: The method will return "c" as the account type.
     */
    public String returnAccType()
    {
        return "c";
    }
    
}